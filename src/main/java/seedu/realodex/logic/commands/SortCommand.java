package seedu.realodex.logic.commands;

import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;

import javafx.collections.ObservableList;
import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.model.Model;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.sorting.BirthdayComparator;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts clients by their birthday\n"
            + "Example: sort";

    public static final String MESSAGE_SORT_HELP = "Sort Command: "
            + "Sorts clients their birthday"
            + "Format: sort]\n"
            + "Example: sort\n";

    public static final String MESSAGE_SUCCESS = "Sorted list by days to their birthday!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Person> copiedInternalListForSorting = model.getCopyOfInternalListOfUniquePersonsList();
        Realodex realodexToUpdate = (Realodex) model.getRealodex();
        copiedInternalListForSorting.sort(new BirthdayComparator());
        realodexToUpdate.setPersons(copiedInternalListForSorting);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
