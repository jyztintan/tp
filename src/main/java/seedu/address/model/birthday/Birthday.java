package seedu.address.model.birthday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a remark in the address book.
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthday should be in dd-MMM-yyyy format";
    public static final DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
    public final Optional<Date> birthday;

    /**
     * Constructs a {@code Remark}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        // checkArgument(isValidBirthday(birthday));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Optional<Date> birthdayDate;
        try {
            birthdayDate = Optional.of(formatter.parse(birthday));
        } catch (ParseException e) {
            // will only reach here with empty string, other cases have been caught in isValidBirthday
            birthdayDate = Optional.empty();
        }
        this.birthday = birthdayDate;
    }

    public static boolean isValidBirthday(String birthday) {
        if (birthday.isBlank()) {
            return true;
        }
        // todo: conduct check without using try-catch block for better code quality
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
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
            return "Birthday: " + df.format(birthday.get());
        } else {
            return "No specified Birthday.";
        }
    }
}
