package seedu.realodex.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.parser.exceptions.ParseException;

public class PrefixCheckerTest {

    @Test
    public void anyPrefixesPresent_withMultiplePrefixes_returnsTrue() {
        String argsString = " n/John Doe e/johnd@example.com ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_EMAIL);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertTrue(prefixChecker.anyPrefixesPresent(PREFIX_NAME, PREFIX_EMAIL));
    }

    @Test
    public void anyPrefixesPresent_withNoPrefixes_returnsFalse() {
        String argsString = " ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_EMAIL);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertFalse(prefixChecker.anyPrefixesPresent(PREFIX_NAME, PREFIX_EMAIL));
    }
    @Test
    public void anyPrefixesPresent_withNoRelevantPrefixes_returnsFalse() {
        String argsString = " n/John Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertFalse(prefixChecker.anyPrefixesPresent(PREFIX_PHONE, PREFIX_ADDRESS));
    }

    @Test
    public void moreThanOnePrefixTypePresent_withMultiplePrefixes_returnsTrue() {
        String argsString = " n/John Doe e/johnd@example.com ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_EMAIL);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertTrue(prefixChecker.moreThanOnePrefixTypePresent(PREFIX_NAME, PREFIX_EMAIL));
    }

    @Test
    public void moreThanOnePrefixTypePresent_withMultipleSamePrefixType_returnsFalse() {
        String argsString = " t/buyer t/seller";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertFalse(prefixChecker.moreThanOnePrefixTypePresent(PREFIX_NAME, PREFIX_EMAIL));
    }

    @Test
    public void moreThanOnePrefixTypePresent_withSinglePrefix_returnsFalse() {
        String argsString = " n/John Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertFalse(prefixChecker.moreThanOnePrefixTypePresent(PREFIX_NAME, PREFIX_EMAIL));
    }

    @Test
    public void checkEmptyPreamble_withEmptyPreamble_returnsTrue() {
        String argsString = " n/John Doe";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertTrue(prefixChecker.checkEmptyPreamble());
    }


    @Test
    public void checkEmptyPreamble_withNonEmptyPreamble_returnsFalse() {
        String argsString = "Random Preamble n/John Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertFalse(prefixChecker.checkEmptyPreamble());
    }

    @Test
    public void checkNoDuplicatePrefix_noDuplicates_doesNotThrowException() {
        String argsString = " n/John Doe e/johnd@example.com ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_EMAIL);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertDoesNotThrow(() -> prefixChecker.checkNoDuplicatePrefix(PREFIX_NAME, PREFIX_EMAIL));
    }

    @Test
    public void checkNoDuplicatePrefix_upToTwoTagPrefixes_doesNotThrowException() {
        String argsString = " t/buyer t/seller ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_TAG);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertDoesNotThrow(() -> prefixChecker.checkNoDuplicatePrefix(PREFIX_TAG));
    }

    @Test
    public void checkNoDuplicatePrefix_moreThanTwoTagPrefixes_throwsParseException() {
        String argsString = " t/buyer t/seller t/buyer ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_TAG);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertThrows(ParseException.class, () -> prefixChecker.checkNoDuplicatePrefix(PREFIX_TAG));
    }

    @Test
    public void checkNoDuplicatePrefix_oneDuplicate_throwsParseException() {
        String argsString = " n/John Doe n/Jane Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertThrows(ParseException.class, () -> prefixChecker.checkNoDuplicatePrefix(PREFIX_NAME));
    }

    @Test
    public void checkNoDuplicatePrefix_multipleDuplicates_throwsParseException() {
        String argsString = " n/John Doe e/johnd@example.com n/Jane Doe e/janed@example.com ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_EMAIL);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertThrows(ParseException.class, () -> prefixChecker.checkNoDuplicatePrefix(PREFIX_NAME, PREFIX_EMAIL));
    }

    @Test
    public void isDuplicatePrefix_nullPrefix_assertsError() {
        String argsString = " n/John Doe n/Jane Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertThrows(AssertionError.class, () -> prefixChecker.isDuplicatePrefix(null));
    }
    @Test
    public void isDuplicatePrefix_regularPrefixWithDuplicates_returnsTrue() {
        String argsString = " n/John Doe n/Jane Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertTrue(prefixChecker.isDuplicatePrefix(PREFIX_NAME));
    }

    @Test
    public void isDuplicatePrefix_regularPrefixWithoutDuplicates_returnsFalse() {
        String argsString = " n/John Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertFalse(prefixChecker.isDuplicatePrefix(PREFIX_NAME));
    }

    @Test
    public void isDuplicatePrefix_specialCasePrefixWithAllowedDuplicates_returnsFalse() {
        String argsString = " t/buyer t/seller ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_TAG);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertFalse(prefixChecker.isDuplicatePrefix(PREFIX_TAG));
    }

    @Test
    public void isDuplicatePrefix_specialCasePrefixExceedingAllowedDuplicates_returnsTrue() {
        String argsString = " t/buyer t/seller t/buyer ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_TAG);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        assertTrue(prefixChecker.isDuplicatePrefix(PREFIX_TAG), "More than two PREFIX_TAG should return true.");
    }

    @Test
    public void isSpecialCasePrefix_nullPrefix_assertsError() {
        PrefixChecker prefixChecker = new PrefixChecker(new ArgumentMultimap());
        assertThrows(AssertionError.class, () -> prefixChecker.isSpecialCasePrefix(null));
    }

    @Test
    public void isSpecialCasePrefix_tagPrefix_returnsTrue() {
        PrefixChecker prefixChecker = new PrefixChecker(new ArgumentMultimap());
        assertTrue(prefixChecker.isSpecialCasePrefix(PREFIX_TAG));
    }

    @Test
    public void isPrefixPresent_presentPrefix_returnsTrue() {
        String argsString = " n/John Doe r/has 3 cats. ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_REMARK);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);
        assertTrue(prefixChecker.isPrefixPresent(PREFIX_NAME));
    }

    @Test
    public void isPrefixPresent_nonPresentPrefix_returnsTrue() {
        String argsString = " n/John Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_REMARK);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);
        assertFalse(prefixChecker.isPrefixPresent(PREFIX_REMARK));
    }

    @Test
    public void isPrefixPresent_nullPrefix_assertsError() {
        String argsString = " n/John Doe ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_REMARK);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);
        assertThrows(AssertionError.class, () -> prefixChecker.isPrefixPresent(null));
    }

    @Test
    void findPresentPrefix_whenSinglePrefixPresent_returnsCorrectPrefix() {
        // Setup ArgumentMultimap with a single prefix
        String argsString = " n/John Doe";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        Prefix result = prefixChecker.findPresentPrefix(PREFIX_NAME, PREFIX_EMAIL);
        assertEquals(PREFIX_NAME, result);
    }

    @Test
    void findPresentPrefix_whenMultiplePrefixesPresent_returnsFirstPresentPrefix() {
        // Setup ArgumentMultimap with multiple prefixes
        String argsString = " n/John Doe r/Loves cats";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_REMARK);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        Prefix result = prefixChecker.findPresentPrefix(PREFIX_NAME, PREFIX_REMARK);
        assertEquals(PREFIX_NAME, result);
    }

    @Test
    void findPresentPrefix_whenNoPrefixesPresent_returnsNull() {
        // Setup ArgumentMultimap with no prefixes
        String argsString = " Just some text without prefixes";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);
        PrefixChecker prefixChecker = new PrefixChecker(argMultimap);

        Prefix result = prefixChecker.findPresentPrefix(PREFIX_NAME, PREFIX_REMARK);
        assertNull(result);
    }

}
