package seedu.realodex.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import seedu.realodex.testutil.PersonBuilder;

class BirthdayIsInMonthPredicateTest {

    @Test
    public void equals() {
        String firstMonth = "jan";
        String secondMonth = "feb";

        BirthdayIsInMonthPredicate firstPredicate = new BirthdayIsInMonthPredicate(firstMonth);
        BirthdayIsInMonthPredicate secondPredicate = new BirthdayIsInMonthPredicate(secondMonth);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BirthdayIsInMonthPredicate firstPredicateCopy = new BirthdayIsInMonthPredicate(firstMonth);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_BirthdayIsInMonth_returnsTrue() {
        // Keyphrase is valid month with 3 letters
        BirthdayIsInMonthPredicate predicate = new BirthdayIsInMonthPredicate("Jan");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("1Jan2001").build()));

        // Keyphrase is valid month with 4 letters
        predicate = new BirthdayIsInMonthPredicate("Sept");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("11Sep2001").build()));

        // Keyphrase is february and Birthday in leap year
        predicate = new BirthdayIsInMonthPredicate("Feb");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("29Feb2000").build()));

        // Mixed-case keyphrase
        predicate = new BirthdayIsInMonthPredicate("jAn");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("1Jan2001").build()));
    }

    @Test
    void and() {
    }

    @Test
    void negate() {
    }

    @Test
    void or() {
    }

    @Test
    void isEqual() {
    }

    @Test
    void not() {
    }

    @Test
    void test1() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void toStringMethod() {
        String keyphrase = "Aug";
        BirthdayIsInMonthPredicate predicate = new BirthdayIsInMonthPredicate(keyphrase);
        String expected = BirthdayIsInMonthPredicate.class.getCanonicalName() + "{month=August}";
        assertEquals(expected, predicate.toString());
    }
}