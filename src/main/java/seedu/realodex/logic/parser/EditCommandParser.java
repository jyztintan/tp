package seedu.realodex.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_HOUSINGTYPE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.logic.commands.EditCommand;
import seedu.realodex.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Birthday;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.tag.Tag;
//@@author UdhayaShan1
/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private static final String MESSAGE_ERROR_PARSING_TAGS = "Error parsing tags: " + Tag.MESSAGE_CONSTRAINTS;

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_FAMILY, PREFIX_TAG, PREFIX_HOUSINGTYPE, PREFIX_REMARK, PREFIX_BIRTHDAY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME, PREFIX_EMAIL, PREFIX_ADDRESS,
                                                 PREFIX_FAMILY, PREFIX_HOUSINGTYPE, PREFIX_REMARK, PREFIX_BIRTHDAY);

        Index index = parseIndex(argMultimap.getPreamble());

        EditPersonDescriptor editPersonDescriptor = parseEditPersonDescriptor(argMultimap);

        return new EditCommand(index, editPersonDescriptor);
    }

    private Index parseIndex(String preamble) throws ParseException {
        try {
            return ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
    }

    private EditPersonDescriptor parseEditPersonDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        StringBuilder errorMessageBuilder = new StringBuilder();

        //For fields with ParserUtilResult implementation
        parseAndSetField(argMultimap, PREFIX_NAME, editPersonDescriptor::setName, ParserUtil::parseNameReturnStored, errorMessageBuilder, "name");
        parseAndSetField(argMultimap, PREFIX_PHONE, editPersonDescriptor::setPhone, ParserUtil::parsePhoneReturnStored, errorMessageBuilder, "phone");
        parseAndSetField(argMultimap, PREFIX_INCOME, editPersonDescriptor::setIncome, ParserUtil::parseIncomeReturnStored, errorMessageBuilder, "income");
        parseAndSetField(argMultimap, PREFIX_EMAIL, editPersonDescriptor::setEmail, ParserUtil::parseEmailReturnStored, errorMessageBuilder, "email");
        parseAndSetField(argMultimap, PREFIX_ADDRESS, editPersonDescriptor::setAddress, ParserUtil::parseAddressReturnStored, errorMessageBuilder, "address");
        parseAndSetField(argMultimap, PREFIX_FAMILY, editPersonDescriptor::setFamily, ParserUtil::parseFamilyReturnStored, errorMessageBuilder, "family");
        parseAndSetField(argMultimap, PREFIX_BIRTHDAY, editPersonDescriptor::setBirthday, ParserUtil::parseBirthdayReturnStored, errorMessageBuilder, "birthday");
        parseAndSetField(argMultimap, PREFIX_HOUSINGTYPE, editPersonDescriptor::setHousingType, ParserUtil::parseHousingTypeReturnStored, errorMessageBuilder, "house type");

        //These fields do not have ParseUtilResult implementation
        if (argMultimap.containsPrefix(PREFIX_TAG)) {
            try {
                Set<Tag> parsedTagsForEdit = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
                editPersonDescriptor.setTags(parsedTagsForEdit);
            } catch (ParseException exception) {
                errorMessageBuilder.append(MESSAGE_ERROR_PARSING_TAGS);
            }
        }

        if (argMultimap.containsPrefix(PREFIX_REMARK)) {
            editPersonDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }

        if (errorMessageBuilder.length() > 0) {
            throw new ParseException(errorMessageBuilder.toString());
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return editPersonDescriptor;
    }

    private <T> void parseAndSetField(ArgumentMultimap argMultimap, Prefix prefix, Consumer<T> setter,
                                      Function<String, ParserUtilResult<T>> parserFunction,
                                      StringBuilder errorMessageBuilder, String fieldName) {
        if (argMultimap.getValue(prefix).isPresent()) {
            ParserUtilResult<T> result = parserFunction.apply(argMultimap.getValue(prefix).get());
            result.buildErrorMessage(errorMessageBuilder, fieldName);
            setter.accept(result.returnStoredResult());
        }
    }
}
//@@author
