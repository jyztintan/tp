package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void isValidBirthday() {
        // null email
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // blank email
        assertTrue(Birthday.isValidBirthday("")); // empty string
        assertTrue(Birthday.isValidBirthday(" ")); // spaces only

        // missing parts
        assertFalse(Birthday.isValidBirthday("1")); // missing month
        assertFalse(Birthday.isValidBirthday("31June")); // missing year
        assertFalse(Birthday.isValidBirthday("June2002")); // missing day

        // invalid parts
        assertFalse(Birthday.isValidBirthday("29Feb2023")); // not a leap year
        assertFalse(Birthday.isValidBirthday("1-jan-2001")); // not supposed to have '-'

        // valid date
        assertTrue(Birthday.isValidBirthday("29Feb2024")); // leap year
        assertTrue(Birthday.isValidBirthday("12May2003"));
        assertTrue(Birthday.isValidBirthday("08Aug1888"));

        // invalid dates
        assertFalse(Birthday.isValidBirthday("01May2009233"));
        assertFalse(Birthday.isValidBirthday("0"));
        assertFalse(Birthday.isValidBirthday("1jan2032")); //cant be in future

    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday("14mar1706"); // pi day!

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday("14mar1706")));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals(5.0f));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday("13mar1706")));
    }

    @Test
    public void birthdayDefaultConstructor_equalsDefault() {
        Birthday defaultBirthday = new Birthday();
        Birthday birthdayWithDefaultValue = new Birthday("01May2023");
        assertEquals(defaultBirthday, birthdayWithDefaultValue);
    }
}
