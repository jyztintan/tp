package seedu.realodex.model.person.predicates;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.realodex.logic.commands.FilterCommand;
import seedu.realodex.logic.parser.Prefix;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.tag.Tag;

/**
 * A factory class to produce different types of {@code Predicate<Person>} based on a given prefix and keyphrase.
 * This class encapsulates the mapping between specific prefixes and the predicates they correspond to.
 */
public class PredicateProducer {
    private Map<Prefix, Function<List<String>, Predicate<Person>>> predicateMap;

    /**
     * Constructs a new {@code PredicateProducer} and
     * initializes the mapping between prefixes and their corresponding predicate constructors.
     */
    public PredicateProducer() {
        predicateMap = new HashMap<>();
        initialize();
    }

    /**
     * Initializes the predicate map with supported prefixes and their associated predicate constructor functions.
     * This method is called during the construction of the {@code PredicateProducer} instance.
     */
    private void initialize() {
        predicateMap.put(PREFIX_NAME, keyphrases -> new NameContainsKeyphrasePredicate(keyphrases.get(keyphrases.size() - 1)));
        predicateMap.put(PREFIX_REMARK, keyphrases -> new RemarkContainsKeyphrasePredicate(keyphrases.get(keyphrases.size() - 1)));
        predicateMap.put(PREFIX_TAG, this::createMatchTagsPredicate);
    }

    /**
     * Creates and returns a {@code Predicate<Person>} based on the provided prefix and list of keyphrases.
     * The predicate tests whether a given {@code Person} object meets the criteria defined by the keyphrases
     * associated with the prefix. For PREFIX_NAME and PREFIX_REMARK, only one keyphrase should be present.
     *
     * @param prefix The {@code Prefix} that specifies the type of predicate to create.
     * @param keyphrases The list of keyphrases to be used in the predicate for testing {@code Person} objects.
     *                   For PREFIX_TAG, all keyphrases in the list are considered in creating the predicate.
     * @return A {@code Predicate<Person>} that tests if a {@code Person} object meets the specified criteria.
     * @throws ParseException if the list of keyphrases is null, empty, contains empty strings, or if the prefix is unhandled.
     */
    public Predicate<Person> createPredicate(Prefix prefix, List<String> keyphrases) throws ParseException {
        if (keyphrases == null || keyphrases.isEmpty() || keyphrases.stream().anyMatch(String::isEmpty)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        Function<List<String>, Predicate<Person>> predicateCreator = predicateMap.get(prefix);
        assert(predicateCreator != null);
        return predicateCreator.apply(keyphrases);

    }

    /**
     * Creates a predicate to evaluate if a {@link Person} has specific tag(s).
     * The predicate checks if the set of tags associated with a person includes the tag(s)
     * created from the provided string.
     *
     * @param tagStrings The string from which tag(s) are created. These tag(s) are then used
     *               in the predicate to check against a person's tags.
     * @return A {@code Predicate<Person>} that tests whether a person's tags include
     *         the tag(s) created from the provided string. The predicate returns {@code true}
     *         if the person's tags contain the specified tag(s), and {@code false} otherwise.
     */
    public Predicate<Person> createMatchTagsPredicate(List<String> tagStrings) {
        Set<Tag> tagSet = tagStrings.stream().map(Tag::new).collect(Collectors.toSet());
        return person -> tagSet.stream().allMatch(tag -> person.getTags().contains(tag));
    }

}
