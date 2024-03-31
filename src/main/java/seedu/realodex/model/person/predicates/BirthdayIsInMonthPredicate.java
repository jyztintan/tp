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
        return person.getBirthday().getOptionalBirthday()
                .map(birthday -> {
                    Calendar personCalendar = Calendar.getInstance();
                    personCalendar.setTime(birthday);
                    return personCalendar.get(Calendar.MONTH) == month.map(m -> m.get(Calendar.MONTH)).orElse(-1);
                })
                .orElse(false);
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
        return new ToStringBuilder(this)
                .add("month", month.map(m -> monthFormat.format(m.getTime()))
                        .orElse("No month specified"))
                .toString();
    }
}
