package seedu.realodex.model.person.sorting;

import java.util.Calendar;
import java.util.Comparator;

import seedu.realodex.model.person.Birthday;
import seedu.realodex.model.person.Person;

public class BirthdayComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getBirthday().toString().isBlank()) {
            return 1;
        }
        if (o2.getBirthday().toString().isBlank()) {
            return 1;
        }
        return o1.getBirthday().getDaysUntilBirthday().compareTo(o2.getBirthday().getDaysUntilBirthday());
    }
}
