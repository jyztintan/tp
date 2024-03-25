package seedu.realodex.logic.parser;

import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.logic.commands.DeleteCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    String INDEX_AND_NAME_PROVIDED = "Please provide either an index or a name, not both.";
    DeleteCommand deleteCommand;
    Name name;
    Index index;

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        String trimmed = args;

        // Parse name if present
        if (namePrefixPresent(argMultimap)) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            trimmed = args.replace("n/" + name.fullName, "");
        }

        // Parse index if present
        try {
            index = ParserUtil.parseIndex(trimmed);
        } catch (ParseException pe) {
            index = null;
        }

        if (namePrefixPresent(argMultimap) && index == null) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            deleteCommand = new DeleteCommand(name);
        } else if (!namePrefixPresent(argMultimap) && index != null) {
            deleteCommand = new DeleteCommand(index);
        } else if (namePrefixPresent(argMultimap) && index != null) {
            throw new ParseException(INDEX_AND_NAME_PROVIDED);
        }
        return deleteCommand;
    }

    /**
     * Returns true if there is a PREFIX_NAME in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean namePrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_NAME).isPresent();
    }
}
