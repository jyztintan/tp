package seedu.realodex.logic.commands;

import javafx.collections.ObservableList;
import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.model.Model;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.sorting.BirthdayComparator;

/**
 * Command to sort clients by their birthday.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts clients by their birthday\n"
            + "Example: sort";

    public static final String MESSAGE_SORT_HELP = "Sort Command: "
            + "Sorts clients by their birthday\n"
            + "Format: sort\n"
            + "Example: sort\n";

    public static final String MESSAGE_SUCCESS = "Sorted list by days to their birthday!";

    /**
     * Executes the sort command to sort clients by their birthday.
     *
     * @param model the current model of the application
     * @return the result of the execution
     * @throws CommandException if an error occurs during execution
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Realodex realodexToUpdate = (Realodex) model.getRealodex();
        ObservableList<Person> copiedInternalListForSorting =
                realodexToUpdate.getCopyOfInternalListOfUniquePersonsList();
        copiedInternalListForSorting.sort(new BirthdayComparator());
        realodexToUpdate.setPersons(copiedInternalListForSorting);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Returns a string representation of this command.
     *
     * @return a string representation of this command
     */
    /*@Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }*/
}
