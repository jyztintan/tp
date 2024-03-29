package seedu.realodex.logic.parser;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.realodex.logic.commands.SortCommand;

import seedu.realodex.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final Prefix[] POSSIBLE_PREFIXES = { };
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, POSSIBLE_PREFIXES);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        checkNoPrefixPresent(prefixChecker);
        return new SortCommand();
    }

    /**
     * Checks if exactly one prefix is present in the input arguments. This method ensures that
     * the command includes at least one recognized prefix and that no more than one is specified,
     * to maintain the command's specificity.
     *
     * @param prefixChecker the {@link PrefixChecker} used to validate the presence of prefixes.
     * @throws ParseException if no prefixes are present or more than one prefix is found.
     */
    private void checkNoPrefixPresent(PrefixChecker prefixChecker) throws ParseException {
        // Check for at least one prefix present and no empty preamble
        if (prefixChecker.anyPrefixesPresent(POSSIBLE_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

}
