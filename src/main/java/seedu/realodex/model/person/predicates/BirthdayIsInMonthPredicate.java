package seedu.realodex.model.person.predicates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag} contains the Tag(s) given.
 */
public class BirthdayIsInMonthPredicate implements Predicate<Person> {
    private final String month;
    private final Date month;
    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");

    public BirthdayIsInMonthPredicate(String monthName) throws ParseException {
        java.util.Date monthDate = monthFormat.parse(monthName);
        Calendar cal = Calendar.getInstance();
        this.keyphrase = keyphrase;
    }

    @Override
    public boolean test(Person person) {
        String nameInLowerCase = person.getName().fullName.toLowerCase();
        String keyphraseInLowerCase = keyphrase.toLowerCase();
        return nameInLowerCase.contains(keyphraseInLowerCase);
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
        return month.equals(BirthdayIsInMonthPredicate.month);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyphrase", keyphrase).toString();
    }

}
