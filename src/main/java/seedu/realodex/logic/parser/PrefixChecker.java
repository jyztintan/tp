package seedu.realodex.logic.parser;

import java.util.stream.Stream;

import seedu.realodex.logic.Messages;
import seedu.realodex.logic.parser.exceptions.ParseException;

import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * A utility class to check the presence and uniqueness of prefixes in an {@code ArgumentMultimap}.
 * This class provides methods to verify if specific prefixes are provided by the user,
 * ensure no duplicate prefixes are used, and more.
 */
public class PrefixChecker {

    private final ArgumentMultimap argumentMultimap;

    /**
     * Constructs a {@code PrefixChecker} with the provided {@code ArgumentMultimap}.
     *
     * @param argumentMultimap The {@code ArgumentMultimap} to be used for prefix checks.
     */
    public PrefixChecker(ArgumentMultimap argumentMultimap) {
        this.argumentMultimap = argumentMultimap;
    }

    /**
     * Checks if any of the provided prefixes appear more times than allowed in the argument multimap.
     * This method leverages {@code isExceedingAllowedOccurrences} to determine if the occurrence count
     * for any given prefix exceeds its permitted limit.
     *
     * @param prefixes An array of {@code Prefix} objects to be checked for excessive occurrences.
     * @return {@code true} if any prefix exceeds its allowed occurrence limit, {@code false} otherwise.
     */
    public boolean anyPrefixesPresent(Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(this::isPrefixPresent);
    }

    /**
     * Determines if more than one of the provided prefixes are present in the {@code ArgumentMultimap}.
     *
     * @param prefixes The prefixes to check.
     * @return {@code true} if more than one prefix is present, {@code false} otherwise.
     */
    public boolean moreThanOnePrefixPresent(Prefix... prefixes) {
        return Stream.of(prefixes)
                .anyMatch(prefix -> isExceedingAllowedOccurrences(prefix));
    }


    /**
     * Determines whether the occurrence count of a given prefix in the argument multimap exceeds the allowed limit.
     * Special case prefixes, identified by {@code isSpecialCasePrefix}, are permitted a higher occurrence count than
     * regular prefixes. This method accounts for such distinctions in its check.
     *
     * @param prefix The {@link Prefix} whose occurrences are to be checked.
     * @return {@code true} if the occurrence count for the prefix exceeds its allowed limit, {@code false} otherwise.
     */
    private boolean isExceedingAllowedOccurrences(Prefix prefix) {
        long count = argumentMultimap.getAllValues(prefix).size();

        if (isSpecialCasePrefix(prefix)) {
            // Allow up to 2 instances for special case prefixes
            return count > 2;
        } else {
            // Only allow 1 instance for regular prefixes
            return count > 1;
        }
    }

    /**
     * Checks if the preamble (text before the first valid prefix) of the {@code ArgumentMultimap} is empty.
     *
     * @return {@code true} if the preamble is empty, {@code false} otherwise.
     */
    public boolean checkEmptyPreamble() {
        return argumentMultimap.getPreamble().isEmpty();
    }

    /**
     * Checks for duplicate prefixes in the given array of prefixes, with an exception for tag prefixes
     * that is allowed to appear at most twice. Throws a {@code ParseException} if duplicates are found.
     *
     * @param prefixes An array of {@code Prefix} objects to be checked for duplicates.
     * @throws ParseException If duplicates are found, except for the allowed exceptions.
     */
    public void checkNoDuplicatePrefix(Prefix... prefixes) throws ParseException {
        Prefix[] duplicatedPrefixes = Stream.of(prefixes)
                .filter(this::isDuplicatePrefix)
                .distinct()
                .toArray(Prefix[]::new);

        if (duplicatedPrefixes.length > 0) {
            throw new ParseException(Messages.getErrorMessageForDuplicatePrefixes(duplicatedPrefixes));
        }
    }

    /**
     * Determines whether a given prefix is considered a duplicate within the context of an {@code ArgumentMultimap}.
     * A special case is made for special prefixes where at most 1 duplicate is allowed.
     *
     * @param prefix The {@link Prefix} to check for duplication.
     * @return {@code true} if the prefix is considered a duplicate (considering the allowed exceptions),
     *         {@code false} otherwise.
     */
    private boolean isDuplicatePrefix(Prefix prefix) {
        if (isSpecialCasePrefix(prefix)) {
            return argumentMultimap.containsPrefix(prefix) && argumentMultimap.getAllValues(prefix).size() > 2;
        } else {
            return argumentMultimap.containsPrefix(prefix) && argumentMultimap.getAllValues(prefix).size() > 1;
        }
    }

    /**
     * Identifies if the given prefix is a special case that is subject to different duplication rules.
     * For example, tag prefixes are allowed to appear more than once but at most twice
     * without being considered duplicates.
     *
     * @param prefix The {@link Prefix} to be checked.
     * @return {@code true} if the prefix is a special case, {@code false} otherwise.
     */
    private boolean isSpecialCasePrefix(Prefix prefix) {
        return prefix.equals(PREFIX_TAG);
    }

    /**
     * Checks if a specific prefix is present in the {@code ArgumentMultimap}.
     *
     * @param prefix The prefix to check for presence.
     * @return {@code true} if the prefix is present, {@code false} otherwise.
     */
    public boolean isPrefixPresent(Prefix prefix) {
        return argumentMultimap.containsPrefix(prefix);
    }

    /**
     * Finds and returns the first prefix that is present in the {@code ArgumentMultimap}
     * from the given list of prefixes.
     *
     * @param prefixes The prefixes to search through.
     * @return The first present prefix, or {@code null} if none are present.
     */
    public Prefix findPresentPrefix(Prefix...prefixes) {
        for (Prefix prefix : prefixes) {
            if (isPrefixPresent(prefix)) {
                return prefix;
            }
        }
        return null;
    }
}
