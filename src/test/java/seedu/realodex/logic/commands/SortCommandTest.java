package seedu.realodex.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.TypicalPersons.getSecondTypicalRealodex;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.model.Model;
import seedu.realodex.model.ModelManager;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.UserPrefs;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.sorting.BirthdayComparator;

public class SortCommandTest {
    private final Model model = new ModelManager(getTypicalRealodex(), new UserPrefs());
    private final Model modelSecond = new ModelManager(getSecondTypicalRealodex(), new UserPrefs());
    @Test
    public void execute_sortsListByBirthday_success() {
        // Create a list of persons with birthdays in random order
        Realodex realodex = (Realodex) model.getRealodex();

        // Execute the sort command
        SortCommand sortCommand = new SortCommand();
        try {
            sortCommand.execute(model);
        } catch (CommandException e) {
            Assertions.fail();
        }

        // Verify that the list is sorted by birthday
        List<Person> sortedList = realodex.getCopyOfInternalListOfUniquePersonsList();
        assertTrue(isSortedByBirthday(sortedList));

        realodex = (Realodex) modelSecond.getRealodex();
        try {
            sortCommand.execute(modelSecond);
        } catch (CommandException e) {
            Assertions.fail();
        }
        sortedList = realodex.getCopyOfInternalListOfUniquePersonsList();
        assertTrue(isSortedByBirthday(sortedList));

    }

    @Test
    public void execute_returnsSuccessMessage() {
        // Execute the sort command
        SortCommand sortCommand = new SortCommand();
        try {
            CommandResult result = sortCommand.execute(model);
            assertEquals(SortCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    // Helper method to check if the list is sorted by birthday
    private boolean isSortedByBirthday(List<Person> persons) {
        for (int i = 1; i < persons.size(); i++) {
            Person previousPerson = persons.get(i - 1);
            Person currentPerson = persons.get(i);
            if (new BirthdayComparator().compare(previousPerson, currentPerson) > 0) {
                return false;
            }
        }
        return true;
    }
}
