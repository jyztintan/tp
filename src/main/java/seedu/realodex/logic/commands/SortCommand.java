package seedu.realodex.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.logic.Messages;
import seedu.realodex.model.Model;
import seedu.realodex.model.person.Person;


/**
 * Sorts and lists all persons in realodex whose name contains the argument keyphrase.
 * Keyphrase matching is case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SORT_HELP = "Sort Command: ";

    public static final String MESSAGE_SORT_CONFLICT = "Sort command can only sort by one field.\n";

    private final Comparator<Person> comparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
//            return -1;
//            return o1.getName().toString().compareTo(o2.getName().toString());
            Optional<Date> b1 = o1.getBirthday().birthday;
            Optional<Date> b2 = o1.getBirthday().birthday;

            if (!b1.isPresent()) {
                return -1;
            }
            return b1.get().compareTo(b2.orElse(b1.get()));
        }
    };

    public SortCommand() { }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getSortedPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return comparator.equals(otherSortCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", comparator)
                .toString();
    }
}
