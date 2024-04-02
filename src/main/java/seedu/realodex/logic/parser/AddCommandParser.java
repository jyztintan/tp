package seedu.realodex.logic.parser;

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

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.AddCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Birthday;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.HousingType;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.person.Remark;
import seedu.realodex.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args The arguments provided by the user.
     * @return The parsed AddCommand object.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = tokenizeArguments(args);

        validateCompulsoryPrefixes(argMultimap);

        validateNoDuplicatePrefixes(argMultimap);

        Person person = buildPerson(argMultimap);

        return new AddCommand(person);
    }

    /**
     * Tokenizes the input arguments.
     *
     * @param args The arguments provided by the user.
     * @return An ArgumentMultimap containing the parsed arguments.
     */
    private ArgumentMultimap tokenizeArguments(String args) {
        return ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME, PREFIX_EMAIL, PREFIX_ADDRESS,
                                          PREFIX_FAMILY, PREFIX_TAG, PREFIX_HOUSINGTYPE,
                                          PREFIX_REMARK, PREFIX_BIRTHDAY);
    }

    /**
     * Validates the presence of compulsory prefixes.
     *
     * @param argMultimap An ArgumentMultimap containing the parsed arguments.
     * @throws ParseException If any compulsory prefix is missing.
     */
    private void validateCompulsoryPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        Prefix[] compulsoryPrefixes = {PREFIX_NAME, PREFIX_ADDRESS, PREFIX_INCOME, PREFIX_PHONE,
            PREFIX_FAMILY, PREFIX_EMAIL,
            PREFIX_TAG, PREFIX_HOUSINGTYPE};
        if (!arePrefixesPresent(argMultimap, compulsoryPrefixes)) {
            String missingPrefixesMessage = argMultimap.returnMessageOfMissingPrefixes(compulsoryPrefixes) + "\n";
            throw new ParseException(Messages.getErrorMessageForMissingPrefixes(missingPrefixesMessage)
                                             + AddCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Validates the absence of duplicate prefixes.
     *
     * @param argMultimap An ArgumentMultimap containing the parsed arguments.
     * @throws ParseException If any duplicate prefix is found.
     */
    private void validateNoDuplicatePrefixes(ArgumentMultimap argMultimap) throws ParseException {
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME, PREFIX_EMAIL, PREFIX_FAMILY,
                                                 PREFIX_ADDRESS, PREFIX_HOUSINGTYPE, PREFIX_REMARK, PREFIX_BIRTHDAY);
    }

    /**
     * Builds a Person object from the parsed arguments.
     *
     * @param argMultimap An ArgumentMultimap containing the parsed arguments.
     * @return The constructed Person object.
     * @throws ParseException If any error occurs during parsing.
     */
    private Person buildPerson(ArgumentMultimap argMultimap) throws ParseException {
        StringBuilder errorMessageBuilder = new StringBuilder();

        Name name = parseName(argMultimap, errorMessageBuilder);
        Phone phone = parsePhone(argMultimap, errorMessageBuilder);
        Income income = parseIncome(argMultimap, errorMessageBuilder);
        Email email = parseEmail(argMultimap, errorMessageBuilder);
        Address address = parseAddress(argMultimap, errorMessageBuilder);
        Family family = parseFamily(argMultimap, errorMessageBuilder);
        Set<Tag> tags = parseTags(argMultimap, errorMessageBuilder);
        HousingType housingType = parseHousingType(argMultimap, errorMessageBuilder);
        Remark remark = parseRemark(argMultimap, errorMessageBuilder);
        ParserUtilResult<Birthday> birthdayStored = ParserUtil
                .parseBirthdayReturnStored(argMultimap
                                                   .getValueOrDefault(PREFIX_BIRTHDAY)
                                                   .orElseThrow());
        birthdayStored.buildErrorMessage(errorMessageBuilder, "birthday");

        if (errorMessageBuilder.length() > 0) {
            throw new ParseException(errorMessageBuilder.toString().trim());
        }
        Birthday birthday = birthdayStored.returnStoredResult();

        return new Person(name, phone, income, email, address, family, tags, housingType, remark, birthday);
    }

    // Methods for parsing individual fields
    private Name parseName(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return parseField(argMultimap, PREFIX_NAME, errorMessageBuilder, ParserUtil::parseNameReturnStored, "name");
    }

    private Phone parsePhone(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return parseField(argMultimap, PREFIX_PHONE, errorMessageBuilder, ParserUtil::parsePhoneReturnStored, "phone");
    }

    private Income parseIncome(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return parseField(argMultimap, PREFIX_INCOME, errorMessageBuilder,
                          ParserUtil::parseIncomeReturnStored,
                          "income");
    }

    private Email parseEmail(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return parseField(argMultimap, PREFIX_EMAIL, errorMessageBuilder,
                          ParserUtil::parseEmailReturnStored, "email");
    }

    private Address parseAddress(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return parseField(argMultimap, PREFIX_ADDRESS, errorMessageBuilder,
                          ParserUtil::parseAddressReturnStored, "address");
    }

    private Family parseFamily(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return parseField(argMultimap, PREFIX_FAMILY, errorMessageBuilder,
                          ParserUtil::parseFamilyReturnStored, "family");
    }

    private Set<Tag> parseTags(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        Set<Tag> tags = null;
        try {
            tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (ParseException e) {
            errorMessageBuilder.append("Error parsing tags: ").append(e.getMessage()).append("\n");
        }
        return tags;
    }

    private HousingType parseHousingType(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return parseField(argMultimap, PREFIX_HOUSINGTYPE, errorMessageBuilder,
                          ParserUtil::parseHousingTypeReturnStored, "housing type");
    }

    private Remark parseRemark(ArgumentMultimap argMultimap, StringBuilder errorMessageBuilder) {
        return ParserUtil.parseRemark(Objects.requireNonNull(argMultimap.getValueOrDefault(PREFIX_REMARK)
                                                                     .orElse(null)));
    }


    // Generic method for parsing individual fields
    private <T> T parseField(ArgumentMultimap argMultimap, Prefix prefix, StringBuilder errorMessageBuilder,
                             Function<String, ParserUtilResult<T>> parserFunction, String fieldName) {
        ParserUtilResult<T> result = parserFunction.apply(argMultimap.getValue(prefix).orElseThrow());
        result.buildErrorMessage(errorMessageBuilder, fieldName);
        return result.returnStoredResult();
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix[] prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
