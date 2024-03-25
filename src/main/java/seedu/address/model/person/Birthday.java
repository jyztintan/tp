package seedu.address.model.person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

/**
 * Represents a remark in the address book.
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthday should be in dd-MMM-yyyy format";
    public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM);
    public static final String INPUT_DATE_PATTERN = "ddMMMyyyy";
    public final Optional<Date> birthday;

    /**
     * Constructs a {@code Remark}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        // checkArgument(isValidBirthday(birthday));
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
     * Returns if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String birthday) {
        if (birthday.isBlank() || birthday.equals("")) {
            return true;
        }
        // todo: conduct check without using try-catch block for better code quality
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(INPUT_DATE_PATTERN, Locale.ENGLISH);
            formatter.setLenient(false);
            formatter.parse(birthday.trim());
            return true;
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
        return birthday.toString();
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
