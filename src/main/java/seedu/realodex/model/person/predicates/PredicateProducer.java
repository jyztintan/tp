package seedu.realodex.model.person.predicates;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

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
    private Map<Prefix, Function<String, Predicate<Person>>> predicateMap;

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
        predicateMap.put(PREFIX_NAME, NameContainsKeyphrasePredicate::new);
        predicateMap.put(PREFIX_REMARK, RemarkContainsKeyphrasePredicate::new);
        predicateMap.put(PREFIX_TAG, this::createMatchTagsPredicate);
    }

    /**
     * Creates and returns a {@code Predicate<Person>} based on the provided prefix and keyphrase.
     * The predicate tests whether a given {@code Person} object meets the criteria
     * defined by the keyphrase associated with the prefix.
     *
     * @param prefix The {@code Prefix} that specifies the type of predicate to create.
     * @param keyphrase The keyphrase to be used in the predicate for testing {@code Person} objects.
     * @return A {@code Predicate<Person>} that tests if a {@code Person} object meets the criteria.
     * @throws ParseException if the keyphrase is null or empty, or if the prefix is unhandled.
     */
    public Predicate<Person> createPredicate(Prefix prefix, String keyphrase) throws ParseException {
        if (keyphrase == null || keyphrase.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        Function<String, Predicate<Person>> predicateCreator = predicateMap.get(prefix);
        assert(predicateCreator != null);
        return predicateCreator.apply(keyphrase.trim());

    }

    /**
     * Creates a predicate to evaluate if a {@link Person} has specific tag(s).
     * The predicate checks if the set of tags associated with a person includes the tag(s)
     * created from the provided string.
     *
     * @param string The string from which tag(s) are created. These tag(s) are then used
     *               in the predicate to check against a person's tags.
     * @return A {@code Predicate<Person>} that tests whether a person's tags include
     *         the tag(s) created from the provided string. The predicate returns {@code true}
     *         if the person's tags contain the specified tag(s), and {@code false} otherwise.
     */
    public Predicate<Person> createMatchTagsPredicate(String string) {
        Set<Tag> personTags = Set.of(new Tag(string));
        return new TagsMatchPredicate(personTags);
    }

}
