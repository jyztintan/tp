package seedu.realodex.model.person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

/**
 * Represents a Birthday in the Realodex
 */
public class Birthday {

    public static final String INPUT_DATE_PATTERN = "ddMMMyyyy";
    public static final String MESSAGE_CONSTRAINTS = "Birthday should be in " + INPUT_DATE_PATTERN + " format\nDate "
            + "should also not be in future years!";
    public static final SimpleDateFormat INPUT_DATE_FORMATTER = new SimpleDateFormat(INPUT_DATE_PATTERN);
    public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM);
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
        Optional<Date> birthdayDate;
        try {
            birthdayDate = Optional.of(formatter.parse("01May2023"));
        } catch (ParseException e) {
            birthdayDate = Optional.empty();
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
                    if (year >= 1 && year <= currentYear) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ParseException e) {
            return false;
        }
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
        if (birthday.isPresent()) {
            return "Birthday: " + DATE_FORMAT.format(birthday.get());
        } else {
            return "No specified Birthday.";
        }
    }
}
