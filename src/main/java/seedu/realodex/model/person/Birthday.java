package seedu.realodex.model.person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.realodex.commons.core.LogsCenter;

/**
 * Represents a Birthday in the Realodex
 */
public class Birthday {

    public static final String INPUT_DATE_PATTERN = "ddMMMMyyyy";
    public static final String MESSAGE_CONSTRAINTS = "Birthday should be in " + INPUT_DATE_PATTERN + " format.\n"
            + "Date should also not be in future years and no earlier than year 1000!\n"
            + "Example: b/6Sep2006 or b/6September2006";

    // for filter purposes
    public static final String FILTER_MONTH_MESSAGE_CONSTRAINTS = "Filter by Birth Month should be in MMMM format.\n"
            + "Example: b/Sep or b/September";
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
            Date currentDate = new Date();
            return !parsedDate.after(currentDate);
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
        return birthday.map(date -> "Birthday: " + DATE_FORMAT.format(date))
                .orElse("No specified Birthday.");
    }
}
