package seedu.realodex.model.person.sorting;

import java.util.Comparator;

import seedu.realodex.model.person.Person;

/**
 * Comparator for sorting persons based on their birthdays.
 */
public class BirthdayComparator implements Comparator<Person> {

    /**
     * Compares two persons based on their birthdays.
     * If one or both persons have unspecified birthdays, they are sorted accordingly.
     * If both persons have specified birthdays, they are sorted based on the number of days until their next birthday.
     *
     * @param o1 the first person to be compared
     * @param o2 the second person to be compared
     * @return a negative integer, zero, or a positive integer as the first person
     *         is less than, equal to, or greater than the second person, respectively
     */
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getBirthday().toString().isBlank()) {
            return 1; // o1 has an unspecified birthday, so it comes after o2
        }
        if (o2.getBirthday().toString().isBlank()) {
            return -1; // o2 has an unspecified birthday, so it comes before o1
        }
        return o1.getBirthday().getDaysUntilBirthday().compareTo(o2.getBirthday().getDaysUntilBirthday());
    }
}
