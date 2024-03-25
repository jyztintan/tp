package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        // assertFalse(Birthday.isValidBirthday("1")); // missing month
        assertFalse(Birthday.isValidBirthday("31-June")); // missing year
        assertFalse(Birthday.isValidBirthday("June-2002")); // missing day

        // invalid parts
        assertFalse(Birthday.isValidBirthday("29-Feb-2023")); // not a leap year

        // valid email
        assertTrue(Birthday.isValidBirthday("29-Feb-2024")); // leap year
        assertTrue(Birthday.isValidBirthday("12-May-2003"));
    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday("14-mar-1706"); // pi day!

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday("14-mar-1706")));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals(5.0f));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday("13-mar-1706")));
    }
}
