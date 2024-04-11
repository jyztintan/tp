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
//@@author 4llysa
public class Birthday {
    public static final String INPUT_DATE_PATTERN = "ddMMMyyyy";
    public static final String INPUT_MONTH_PATTERN = "MMM";
    public static final String MESSAGE_CONSTRAINTS = "Birthday should be in " + "ddMMMyyyy" + " format.\n"
            + "Date should also not be in future years and no earlier than year 1000!\n"
            + "Example: b/17Sep2001";

    // for filter purposes
    public static final String FILTER_MONTH_MESSAGE_CONSTRAINTS = "Birth Month should be in MMM format.\n"
            + "Example: b/Sep";
    public static final SimpleDateFormat INPUT_DATE_FORMATTER = new SimpleDateFormat(INPUT_DATE_PATTERN);
    public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM);
    private static final Logger logger = LogsCenter.getLogger(Birthday.class);
    private final Optional<Date> optionalBirthday;
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
            assert birthdayDate.isPresent();
        } catch (ParseException e) {
            birthdayDate = Optional.empty();
        }
        this.optionalBirthday = birthdayDate;
    }

    /**
     * Constructs a default {@code Birthday}.
     */
    public Birthday() {
        this("");
    }
    /**
     * Returns the optional birthday date.
     *
     * @return The optional birthday date.
     */
    public Optional<Date> getOptionalBirthday() {
        return optionalBirthday;
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
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            return !parsedDate.after(currentDate) && calendar.get(Calendar.YEAR) >= 1000;
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
        return optionalBirthday.equals(otherBirthday.optionalBirthday);
    }

    @Override
    public int hashCode() {
        return optionalBirthday.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return optionalBirthday.map(INPUT_DATE_FORMATTER::format).orElse("");
    }

    //@@author UdhayaShan1
    /**
     * Format state as text for representation.
     */
    public String toStringWithRepresentation() {
        return optionalBirthday.map(date -> "Birthday: " + DATE_FORMAT.format(date))
                .orElse("No specified Birthday.");
    }

    /**
     * Returns the number of days from the current system date to the birthday.
     * If the birthday has already passed this year, it returns the number of days
     * from the current date of next year to the birthday.
     */
    public Long getDaysUntilBirthday() {
        Date currentDate = new Date(); // Current system date
        Date birthdayDate = optionalBirthday.orElse(currentDate); // If birthday is not present, use current date

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

    public String getDaysUntilBirthdayWithRepresentation() {
        if (this.optionalBirthday.isPresent()) {
            return getDaysUntilBirthday() + " More Days Till Their Birthday!";
        }
        return "Birthday is unspecified!";
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
//@@author
