package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void isValidBirthday_test() {
        // null date
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // blank dates
        assertTrue(Birthday.isValidBirthday("")); // empty string
        assertTrue(Birthday.isValidBirthday(" ")); // spaces only

        // missing parts
        assertFalse(Birthday.isValidBirthday("1")); // missing month
        assertFalse(Birthday.isValidBirthday("31June")); // missing year
        assertFalse(Birthday.isValidBirthday("June2002")); // missing day

        // invalid parts
        assertFalse(Birthday.isValidBirthday("29Feb2023")); // not a leap year
        assertFalse(Birthday.isValidBirthday("31June2023")); // June does not have 31 days
        assertFalse(Birthday.isValidBirthday("1-jan-2001")); // not supposed to have '-'

        // valid date
        assertTrue(Birthday.isValidBirthday("29Feb2024")); // leap year
        assertTrue(Birthday.isValidBirthday("12May2003"));
        assertTrue(Birthday.isValidBirthday("08Aug1888"));

        // invalid dates
        assertFalse(Birthday.isValidBirthday("01May2009233"));
        assertFalse(Birthday.isValidBirthday("0"));

        // creating future dates
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate futureDate = today.plusYears(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String tomorrowFormatted = tomorrow.format(formatter);
        String futureFormatted = futureDate.format(formatter);

        // future dates
        assertFalse(Birthday.isValidBirthday(tomorrowFormatted)); //cant be in future days
        assertFalse(Birthday.isValidBirthday(futureFormatted)); //cant be in future years

    }

    @Test
    public void defensiveCheckIsValidBirthday_test() {
        // null date
        assertThrows(NullPointerException.class, () -> Birthday.defensiveCheckIsValidBirthday(null));

        // missing parts
        assertFalse(Birthday.defensiveCheckIsValidBirthday("1")); // missing month
        assertFalse(Birthday.defensiveCheckIsValidBirthday("31June")); // missing year
        assertFalse(Birthday.defensiveCheckIsValidBirthday("June2002")); // missing day

        // invalid parts
        assertFalse(Birthday.defensiveCheckIsValidBirthday("29Feb2023")); // not a leap year
        assertFalse(Birthday.defensiveCheckIsValidBirthday("31June2023")); // June does not have 31 days
        assertFalse(Birthday.defensiveCheckIsValidBirthday("1-jan-2001")); // not supposed to have '-'

        // valid date
        assertTrue(Birthday.defensiveCheckIsValidBirthday("29Feb2024")); // leap year
        assertTrue(Birthday.defensiveCheckIsValidBirthday("12May2003"));
        assertTrue(Birthday.defensiveCheckIsValidBirthday("08Aug1888"));

        // invalid dates
        assertFalse(Birthday.defensiveCheckIsValidBirthday("01May2009233"));
        assertFalse(Birthday.defensiveCheckIsValidBirthday("0"));

        // creating future dates
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate futureDate = today.plusYears(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String tomorrowFormatted = tomorrow.format(formatter);
        String futureFormatted = futureDate.format(formatter);

        // future dates
        assertFalse(Birthday.defensiveCheckIsValidBirthday(tomorrowFormatted)); //cant be in future days
        assertFalse(Birthday.defensiveCheckIsValidBirthday(futureFormatted)); //cant be in future years
    }

    @Test
    public void equals_test() {
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
    public void birthdayDefaultConstructor_equalsDefault_test() {
        Birthday defaultBirthday = new Birthday();
        Birthday birthdayWithDefaultValue = new Birthday("01May2023");
        assertEquals(defaultBirthday, birthdayWithDefaultValue);
    }
}
