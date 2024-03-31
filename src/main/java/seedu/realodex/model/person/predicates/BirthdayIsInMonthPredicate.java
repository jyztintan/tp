package seedu.realodex.model.person.predicates;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Birthday} is in the Month given.
 */
public class BirthdayIsInMonthPredicate implements Predicate<Person> {
    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
    private final Optional<Calendar> month;

    /**
     * Creates object {@code BirthdayIsInMonthPredicate}
     * @param monthName month to be tested
     */
    public BirthdayIsInMonthPredicate(String monthName) {
        Optional<Calendar> tempMonth;
        Date monthDate;
        try {
            monthDate = monthFormat.parse(monthName);
            tempMonth = Optional.of(Calendar.getInstance());
            tempMonth.get().setTime(monthDate);
        } catch (java.text.ParseException e) {
            // will not reach here because Parser checks for valid month
            assert false;
            tempMonth = Optional.empty();
        }

        this.month = tempMonth;
    }

    @Override
    public boolean test(Person person) {
        Calendar personCalendar = Calendar.getInstance();
        if (person.getBirthday().getOptionalBirthday().isEmpty()) {
            return false;
        } else {
            assert(person.getBirthday().getOptionalBirthday().isPresent());
            personCalendar.setTime(person.getBirthday().getOptionalBirthday().get());
            return personCalendar.get(Calendar.MONTH) == month.get().get(Calendar.MONTH);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BirthdayIsInMonthPredicate)) {
            return false;
        }

        BirthdayIsInMonthPredicate otherBirthdayIsInMonthPredicate = (BirthdayIsInMonthPredicate) other;
        return month.equals(otherBirthdayIsInMonthPredicate.month);
    }

    @Override
    public String toString() {
        if (this.month.isPresent()) {
            return new ToStringBuilder(this).add("month", monthFormat.format(month.get().getTime())).toString();
        }
        // will not reach here because Parser checks for valid month
        assert false;
        return "";
    }

}
