package seedu.realodex.model.person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.realodex.commons.core.LogsCenter;

/**
 * Represents a Birthday in the Realodex
 */
public class Birthday {

    public static final String INPUT_DATE_PATTERN = "ddMMMyyyy";
    public static final String MESSAGE_CONSTRAINTS = "Birthday should be in " + INPUT_DATE_PATTERN + " format\nDate "
            + "should also not be in future years and no earlier than year 1000!";
    public static final SimpleDateFormat INPUT_DATE_FORMATTER = new SimpleDateFormat(INPUT_DATE_PATTERN);
    public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM);
    private static final Logger logger = LogsCenter.getLogger(Birthday.class);
    public final Optional<Date> birthday;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        SimpleDateFormat formatter = new SimpleDateFormat(INPUT_DATE_PATTERN, Locale.ENGLISH);
        formatter.setLenient(false);
        Optional<Date> birthdayDate;
        try {
            birthdayDate = Optional.of(formatter.parse(birthday));
        } catch (ParseException e) {
            // will only reach here with empty string, other cases have been caught in isValidBirthday
            birthdayDate = Optional.empty();
        }
        this.birthday = birthdayDate;
    }

    /**
     * Constructs a default {@code Birthday}.
     */
    public Birthday() {
        SimpleDateFormat formatter = new SimpleDateFormat(INPUT_DATE_PATTERN, Locale.ENGLISH);
        formatter.setLenient(false);
        Optional<Date> birthdayDate = Optional.empty();
        try {
            birthdayDate = Optional.of(formatter.parse("01May2023"));
        } catch (ParseException ignored) {
            logger.fine("This part of birthday should not be executed");
        }
        this.birthday = birthdayDate;
    }

    /**
     * Returns if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String birthday) {
        if (birthday.isBlank() || birthday.isEmpty()) {
            return true;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(INPUT_DATE_PATTERN, Locale.ENGLISH);
            formatter.setLenient(false);
            Date parsedDate = formatter.parse(birthday.trim());

            // Validate the month (1-12)
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);
            int month = cal.get(Calendar.MONTH);
            if (month >= Calendar.JANUARY && month <= Calendar.DECEMBER) {
                // Validate the day (1-31)
                int day = cal.get(Calendar.DAY_OF_MONTH);
                if (day >= 1 && day <= 31) {
                    // Validate the year (e.g., not in the future)
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int year = cal.get(Calendar.YEAR);
                    if (year >= 1000 && year <= currentYear) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Returns the day of the birthday.
     */
    public int getDay() {
        return birthday.map(date -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.DAY_OF_MONTH);
        }).orElse(0); // Return 0 if no birthday date is present
    }

    /**
     * Returns the month of the birthday.
     */
    public int getMonth() {
        return birthday.map(date -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.MONTH) + 1; // Add 1 because Calendar months are zero-based
        }).orElse(0); // Return 0 if no birthday date is present
    }

    /**
     * Returns the year of the birthday.
     */
    public int getYear() {
        return birthday.map(date -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.YEAR);
        }).orElse(0); // Return 0 if no birthday date is present
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return birthday.equals(otherBirthday.birthday);
    }

    @Override
    public int hashCode() {
        return birthday.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return birthday.map(INPUT_DATE_FORMATTER::format).orElse("");
    }

    /**
     * Format state as text for representation.
     */
    public String toStringWithRepresentation() {
        return birthday.map(date -> "Birthday: " + DATE_FORMAT.format(date)).orElse("No specified Birthday.");
    }

    public Date getDate() {
        return birthday.orElse(null);
    }

    /**
     * Returns the number of days from the current system date to the birthday.
     */
    /**
     * Returns the number of days from the current system date to the birthday.
     * If the birthday has already passed this year, it returns the number of days
     * from the current date of next year to the birthday.
     */
    public Long getDaysUntilBirthday() {
        Date currentDate = new Date(); // Current system date
        Date birthdayDate = birthday.orElse(currentDate); // If birthday is not present, use current date

        // Remove a time component from the dates for accurate comparison
        Calendar currentCal = returnInstanceOfCalendar(currentDate);
        Calendar birthdayCal = returnInstanceOfCalendar(birthdayDate);

        // Check if the birthday has already passed this year
        if (currentCal.after(birthdayCal)) {
            // If yes, set the birthday year to next year
            birthdayCal.add(Calendar.YEAR, 1);
        }

        // Calculate the difference in milliseconds between the two dates and convert it to days
        long diff = birthdayCal.getTimeInMillis() - currentCal.getTimeInMillis();
        return diff / (1000 * 60 * 60 * 24); // Convert milliseconds to days
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
