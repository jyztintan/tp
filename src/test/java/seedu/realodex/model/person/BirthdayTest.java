package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void getBirthday_test() {
        Birthday birthday = new Birthday("15Jun2023");
        Birthday emptyBirthday = new Birthday("");
        String dateString = "15Jun2023";
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy", Locale.ENGLISH);

        assertEquals(dateString, formatter.format(birthday.getOptionalBirthday().orElse(null)));
        assertEquals(Optional.empty(), emptyBirthday.getOptionalBirthday());
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

    @Test
    public void getDaysUntilBirthday_birthdayInFuture_returnsCorrectDays() throws java.text.ParseException {
        // Set current date to 2023-01-01
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();

        // Set birthday to 2023-01-15
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
        Date birthdayDate = formatter.parse("15Jan2001");

        // Create Birthday object with birthday in future
        Birthday birthday = new Birthday();
        birthday.setOptionalBirthday(Optional.of(birthdayDate));

        // Test
        assertEquals(getDaysUntilBirthdayStub(currentDate, birthdayDate), birthday.getDaysUntilBirthday());
    }

    @Test
    public void getDaysUntilBirthday_birthdayPassedInCurrentYear_returnsCorrectDays() throws java.text.ParseException {
        // Set current date to 2023-01-01
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();

        // Set birthday to 2022-12-15
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
        Date birthdayDate = formatter.parse("15Dec2001");

        // Create Birthday object with birthday passed in current year
        Birthday birthday = new Birthday();
        birthday.setOptionalBirthday(Optional.of(birthdayDate));

        // Test
        assertEquals(getDaysUntilBirthdayStub(currentDate, birthdayDate), birthday.getDaysUntilBirthday());
    }

    @Test
    public void toStringWithRepresentation_birthdayNotPresent_returnsDefaultString() {
        // Create Birthday object with birthday not present
        Birthday birthday = new Birthday();
        birthday.setOptionalBirthday(Optional.empty());

        // Test
        assertEquals("No specified Birthday.", birthday.toStringWithRepresentation());
    }

    @Test
    public void getDaysUntilBirthdayWithRepresentation_birthdayPresent_returnsDaysRepresentation() {
        // Set current date to 2023-01-01
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();

        // Set birthday to 2023-01-15
        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.set(2023, Calendar.JANUARY, 15);
        Date birthdayDate = birthdayCalendar.getTime();

        // Create Birthday object with birthday present
        Birthday birthday = new Birthday();
        birthday.setOptionalBirthday(Optional.of(birthdayDate));

        // Test
        assertEquals(getDaysUntilBirthdayWithRepresentationStub(currentDate, birthdayDate),
                     birthday.getDaysUntilBirthdayWithRepresentation());
    }

    @Test
    public void getDaysUntilBirthdayWithRepresentation_birthdayNotPresent_returnsDefaultString() {
        // Create Birthday object with birthday not present
        Birthday birthday = new Birthday();
        birthday.setOptionalBirthday(Optional.empty());

        // Test
        assertEquals("Birthday is unspecified!", birthday.getDaysUntilBirthdayWithRepresentation());
    }

    @Test
    public void hashCodeTest() {
        Birthday birthday1 = new Birthday("27May2003");
        Birthday birthday2 = new Birthday("27May2003");
        Birthday birthday3 = new Birthday("6June2006");

        // same object -> returns true
        assertTrue(birthday1.hashCode() == birthday1.hashCode());

        // same values -> returns true
        assertTrue(birthday1.hashCode() == birthday2.hashCode());

        // different values -> returns false
        assertFalse(birthday1.hashCode() == birthday3.hashCode());
    }

    /**
     * Returns the number of days from the current system date to the birthday.
     * If the birthday has already passed this year, it returns the number of days
     * from the current date of next year to the birthday.
     */
    private Long getDaysUntilBirthdayStub(Date currentDate, Date birthdayDate) {
        // Remove time component from the dates for accurate comparison
        Calendar currentCal = returnInstanceOfCalendar(currentDate);
        Calendar birthdayCal = returnInstanceOfCalendar(birthdayDate);

        int currentYear = currentCal.get(Calendar.YEAR);
        int currentMonth = currentCal.get(Calendar.MONTH);
        int currentDay = currentCal.get(Calendar.DAY_OF_MONTH);
        int birthdayMonth = birthdayCal.get(Calendar.MONTH);
        int birthdayDay = birthdayCal.get(Calendar.DAY_OF_MONTH);

        // Check if the birthday has already passed this year
        if (currentMonth > birthdayMonth || (currentMonth == birthdayMonth && currentDay > birthdayDay)) {
            // If yes, set the birthday year to the next year
            birthdayCal.set(Calendar.YEAR, currentYear + 1);
        } else {
            birthdayCal.set(Calendar.YEAR, currentYear);
        }

        // Calculate the difference in milliseconds between the two dates and convert it to days
        long diff = birthdayCal.getTimeInMillis() - currentCal.getTimeInMillis();
        return diff / (1000 * 60 * 60 * 24); // Convert milliseconds to days
    }

    public String getDaysUntilBirthdayWithRepresentationStub(Date currentDate, Date birthdayDate) {
        return getDaysUntilBirthdayStub(currentDate, birthdayDate) + " More Days Till Their Birthday!";

    }

    private Calendar returnInstanceOfCalendar(Date date) {
        Calendar toReturn = Calendar.getInstance();
        toReturn.setTime(date);
        toReturn.set(Calendar.HOUR_OF_DAY, 0);
        toReturn.set(Calendar.MINUTE, 0);
        toReturn.set(Calendar.SECOND, 0);
        toReturn.set(Calendar.MILLISECOND, 0);
        return toReturn;
    }
}
