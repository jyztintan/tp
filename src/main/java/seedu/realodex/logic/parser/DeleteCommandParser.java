package seedu.realodex.logic.parser;

import static seedu.realodex.logic.commands.DeleteCommand.MESSAGE_INDEX_AND_NAME_PROVIDED;
import static seedu.realodex.logic.commands.DeleteCommand.MESSAGE_NO_FIELDS_PROVIDED;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.logic.commands.DeleteCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);
        String trimmed = args;

        // Parse name if present
        Name name = null;
        if (namePrefixPresent(argMultimap)) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            trimmed = args.replace("n/" + name.fullName, "");
        }

        Index index = null;
        String checkPreamble = argMultimap.getPreamble();
        if (!checkPreamble.isBlank()) {
            index = ParserUtil.parseIndex(trimmed);
        }



        DeleteCommand deleteCommand;
        if (namePrefixPresent(argMultimap) && checkPreamble.isBlank()) {
            assert name != null;
            deleteCommand = new DeleteCommand(name);
        } else if (!namePrefixPresent(argMultimap) && !checkPreamble.isBlank()) {
            deleteCommand = new DeleteCommand(index);
        } else if (namePrefixPresent(argMultimap) && !checkPreamble.isBlank()) {
            throw new ParseException(MESSAGE_INDEX_AND_NAME_PROVIDED);
        } else {
            throw new ParseException(MESSAGE_NO_FIELDS_PROVIDED);
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
