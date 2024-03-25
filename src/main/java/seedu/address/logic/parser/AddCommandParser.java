package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import static seedu.address.logic.parser.CliSyntax.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.birthday.Birthday;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Family;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME,
                                           PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_FAMILY, PREFIX_TAG, PREFIX_REMARK, PREFIX_BIRTHDAY);

        if (!arePrefixesPresent(argMultimap,
                                PREFIX_NAME,
                                PREFIX_ADDRESS,
                                PREFIX_INCOME,
                                PREFIX_PHONE,
                                PREFIX_FAMILY,
                                PREFIX_EMAIL,
                                PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME, PREFIX_EMAIL,
                                                 PREFIX_FAMILY, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_BIRTHDAY);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Income income = ParserUtil.parseIncome(argMultimap.getValue(PREFIX_INCOME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Family family = ParserUtil.parseFamily(argMultimap.getValue(PREFIX_FAMILY).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Remark remark = ParserUtil.parseRemark(argMultimap.getValueOrDefault(PREFIX_REMARK).get());
        Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValueOrDefault(PREFIX_BIRTHDAY).get());

        Person person = new Person(name, phone, income, email, address, family, tagList, remark, birthday);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
