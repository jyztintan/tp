package seedu.realodex.model.person.predicates;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

import javax.swing.text.html.Option;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.model.person.Person;
import seedu.realodex.logic.parser.exceptions.ParseException;
/**
 * Tests that a {@code Person}'s {@code Birthday} is in the Month given.
 */
public class BirthdayIsInMonthPredicate implements Predicate<Person> {
    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
    private final Optional<Calendar> month;
    public BirthdayIsInMonthPredicate(String monthName) {
        Optional<Calendar> tempMonth;
        Date monthDate;
        try {
            monthDate = monthFormat.parse(monthName);
            tempMonth = Optional.of(Calendar.getInstance());
            tempMonth.get().setTime(monthDate);
        } catch (java.text.ParseException e) {
            // will not reach here because check should have been done
            tempMonth = Optional.empty();
        }

        this.month = tempMonth;
    }

    @Override
    public boolean test(Person person) {
        Calendar personCalendar = Calendar.getInstance();
        if (person.getBirthday().birthday.isEmpty()) {
            return false;
        } else {
            assert(person.getBirthday().birthday.isPresent());
            personCalendar.setTime(person.getBirthday().birthday.get());
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
        return new ToStringBuilder(this).add("month", monthFormat.format(month)).toString();
    }

}
