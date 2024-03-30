package seedu.realodex.logic.commands;

import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.model.Model;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts clients by their birthday\n"
            + "Example: sort";

    public static final String MESSAGE_SORT_HELP = "Sort Command: "
            + "Sorts clients their birthday"
            + "Format: sort]\n"
            + "Example: sort\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
