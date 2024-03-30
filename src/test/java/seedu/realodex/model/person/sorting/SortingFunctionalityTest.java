package seedu.realodex.model.person.sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.realodex.model.person.Person;
import seedu.realodex.testutil.PersonBuilder;

public class SortingFunctionalityTest {

    @Test
    public void SortingFunctionality_test() {

        LocalDate today = LocalDate.now();
        LocalDate date1 = today.minusDays(100); // 265 days to bday
        LocalDate date2 = today.minusDays(3); // 362 days to bday
        LocalDate date3 = today.minusDays(234); // 131 days to bday
        LocalDate date4 = today.minusDays(400); // 330 days to bday
        LocalDate date5 = today.minusDays(10); // 355 days to bday
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        String date1Formatted = date1.format(formatter);
        String date2Formatted = date2.format(formatter);
        String date3Formatted = date3.format(formatter);
        String date4Formatted = date4.format(formatter);
        String date5Formatted = date5.format(formatter);

        ObservableList<Person> unsortedList = javafx.collections.FXCollections.observableArrayList();
        unsortedList.add(new PersonBuilder().withName("Alice Pauline")
                .withIncome("10000")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253")
                .withFamily("4")
                .withTags("buyer")
                .withRemark("this is a remark")
                .build());
        unsortedList.add(new PersonBuilder().withName("Benson Meier")
                .withIncome("20000")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withFamily("4")
                .withTags("seller", "buyer").withBirthday(date1Formatted).build());
        unsortedList.add(new PersonBuilder().withName("Carl Kurz")
                .withPhone("95352563")
                .withIncome("30000")
                .withEmail("heinz@example.com").withAddress("wall street").withTags("buyer")
                .withRemark("Carl was supposed to start with a K, but the doctor misspelled when he was born")
                .withBirthday(date2Formatted).build());
        unsortedList.add(new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
                .withIncome("40000")
                .withEmail("cornelia@example.com").withAddress("10th street").withTags("buyer")
                .withRemark("White VANS").withBirthday(date3Formatted).build());
        unsortedList.add(new PersonBuilder().withName("Fiona Kunz")
                .withPhone("9482427")
                .withIncome("60000")
                .withEmail("lydia@example.com").withAddress("little tokyo").withTags("seller")
                .withBirthday(date4Formatted).build());
        unsortedList.add(new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
                .withEmail("hans@example.com").withAddress("chicago ave")
                .withTags("buyer").withBirthday(date5Formatted).build());

        BirthdayComparator bc = new BirthdayComparator();
        unsortedList.sort(bc);
        String target = "Daniel Meier, Benson Meier, Fiona Kunz, Ida Mueller, Carl Kurz, Alice Pauline, ";
        String result = "";
        for (Person p : unsortedList) {
            result += p.getName() + ", ";
        }
        assertEquals(target, result);
    }
}
