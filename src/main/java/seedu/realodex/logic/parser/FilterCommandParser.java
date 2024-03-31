package seedu.realodex.logic.parser;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.function.Predicate;

import seedu.realodex.logic.commands.FilterCommand;
import static seedu.realodex.logic.parser.CliSyntax.*;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.predicates.PredicateProducer;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    private static final Prefix[] POSSIBLE_PREFIXES = { PREFIX_NAME, PREFIX_REMARK, PREFIX_TAG, PREFIX_BIRTHDAY };
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, POSSIBLE_PREFIXES);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        validateInput(prefixChecker);

        Prefix presentPrefix = prefixChecker.findPresentPrefix(POSSIBLE_PREFIXES);
        List<String> keyphrases = argMultimap.getAllValues(presentPrefix);
        Predicate<Person> predicate = createPredicateForPrefix(presentPrefix, keyphrases);

        return new FilterCommand(predicate);
    }

    /**
     * Creates the appropriate predicate based on the provided prefix and keyphrases.
     *
     * @param presentPrefix The prefix determining the type of predicate to create.
     * @param keyphrases The list of keyphrases to use in the predicate.
     * @return A predicate corresponding to the prefix and keyphrases.
     * @throws ParseException if there's an issue creating the predicate.
     */

    private Predicate<Person> createPredicateForPrefix(Prefix presentPrefix, List<String> keyphrases)
            throws ParseException {
        checkValidNameIfApplicable(presentPrefix, keyphrases);
        checkValidTagsIfApplicable(presentPrefix, keyphrases);
        checkValidBirthdayIfApplicable(presentPrefix, keyphrases);
        PredicateProducer predicateProducer = new PredicateProducer();
        return predicateProducer.createPredicate(presentPrefix, keyphrases);
    }

    /**
     * Validates the input arguments using various checks to ensure conformity to syntax requirements.
     *
     * @param prefixChecker The PrefixChecker to use for validation checks.
     * @throws ParseException if validation fails.
     */
    private void validateInput(PrefixChecker prefixChecker) throws ParseException {
        checkEmptyPreamble(prefixChecker);
        checkOnlyOnePrefixTypePresent(prefixChecker);
        checkNoDuplicatePrefix(prefixChecker);
    }

    /**
     * Checks if exactly one prefix is present in the input arguments. This method ensures that
     * the command includes at least one recognized prefix and that no more than one is specified,
     * to maintain the command's specificity.
     *
     * @param prefixChecker the {@link PrefixChecker} used to validate the presence of prefixes.
     * @throws ParseException if no prefixes are present or more than one prefix is found.
     */
    private void checkOnlyOnePrefixTypePresent(PrefixChecker prefixChecker) throws ParseException {
        // Check for at least one prefix present and no empty preamble
        if (!prefixChecker.anyPrefixesPresent(POSSIBLE_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        if (prefixChecker.moreThanOnePrefixTypePresent(POSSIBLE_PREFIXES)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_FILTER_CONFLICT
            ));
        }
    }

    /**
     * Checks if the preamble (text before the first valid prefix) is empty. This method ensures
     * that there are no extraneous text before the command's key information, adhering to the
     * command's syntax requirements.
     *
     * @param prefixChecker the {@link PrefixChecker} used to examine the preamble.
     * @throws ParseException if the preamble is not empty.
     */
    private void checkEmptyPreamble(PrefixChecker prefixChecker) throws ParseException {
        if (!prefixChecker.checkEmptyPreamble()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks for duplicate prefixes in the input arguments. Duplicate prefixes can lead to ambiguity
     * in the command's intention, so this method ensures that a recognized prefix is used at most
     * once in the command.
     *
     * @param prefixChecker the {@link PrefixChecker} used to detect duplicate prefixes.
     * @throws ParseException if duplicate prefixes are found.
     */
    private void checkNoDuplicatePrefix(PrefixChecker prefixChecker) throws ParseException {
        prefixChecker.checkNoDuplicatePrefix(POSSIBLE_PREFIXES);
    }

    /**
     * Validates name keyphrase if the present prefix is for a name. Each keyphrase must conform
     * to Name constraints.
     *
     * @param presentPrefix The prefix to check if it's name-related.
     * @param keyphrases The list of keyphrases representing potential names.
     * @throws ParseException if any name keyphrase is invalid.
     */
    private void checkValidNameIfApplicable(Prefix presentPrefix, List<String> keyphrases) throws ParseException {
        if (!presentPrefix.equals(PREFIX_NAME)) {
            return;
        }
        String name = keyphrases.get(keyphrases.size() - 1);
        ParserUtil.parseName(name);
    }

    /**
     * Validates tag keyphrases if the present prefix is for tags. Each keyphrase must conform
     * to tag naming constraints.
     *
     * @param presentPrefix The prefix to check if it's tag-related.
     * @param keyphrases The list of keyphrases representing potential tags.
     * @throws ParseException if any tag keyphrase is invalid.
     */
    private void checkValidTagsIfApplicable(Prefix presentPrefix, List<String> keyphrases) throws ParseException {
        if (!presentPrefix.equals(PREFIX_TAG)) {
            return;
        }
        ParserUtil.parseTags(keyphrases);
    }

    /**
     * Validates month keyphrase if the present prefix is for a birthday. Each keyphrase must conform
     * to the format "MMM".
     *
     * @param presentPrefix The prefix to check if it's month-related.
     * @param keyphrases The list of keyphrases representing potential months.
     * @throws ParseException if any month keyphrase is invalid.
     */
    private void checkValidBirthdayIfApplicable(Prefix presentPrefix, List<String> keyphrases) throws ParseException {
        if (!presentPrefix.equals(PREFIX_BIRTHDAY)) {
            return;
        }
        String month = keyphrases.get(keyphrases.size() - 1);
        ParserUtil.parseMonth(month);
    }
}
