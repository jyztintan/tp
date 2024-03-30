package seedu.realodex.model.person.sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.person.Person;
import seedu.realodex.testutil.PersonBuilder;

public class BirthdayComparatorTest {

    @Test
    public void compare_bothPersonsHaveSpecifiedBirthdays() {
        // Create two persons with specified birthdays
        Person validPerson = new PersonBuilder().withBirthday("01Jan1990").build();
        Person validPerson2 = new PersonBuilder().withBirthday("15Feb1992").build();

        // Create the comparator
        BirthdayComparator comparator = new BirthdayComparator();

        // Compare the two persons
        int result = comparator.compare(validPerson, validPerson2);

        // Verify the expected outcome
        assertEquals(-1, result); // Expected result based on the specified birthdays
    }

    @Test
    public void compare_onePersonHaveSpecifiedBirthday() {
        // Create one person with, one person without specified birthday
        Person validPerson = new PersonBuilder().withBirthday("").build();
        Person validPerson2 = new PersonBuilder().withBirthday("15Feb1992").build();

        // Create the comparator
        BirthdayComparator comparator = new BirthdayComparator();

        // Compare the two persons
        int result = comparator.compare(validPerson, validPerson2);
        int result2 = comparator.compare(validPerson2, validPerson);

        // Verify the expected outcome
        assertEquals(1, result); // validperson over validperson2

        assertEquals(-1, result2); // validperson2 over validperson
    }

    // Write similar test cases for other scenarios
}

