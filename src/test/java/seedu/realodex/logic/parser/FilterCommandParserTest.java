package seedu.realodex.logic.parser;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseSuccess;
import seedu.realodex.model.person.Birthday;
import seedu.realodex.model.person.predicates.BirthdayIsInMonthPredicate;
import static seedu.realodex.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.FilterCommand;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.predicates.NameContainsKeyphrasePredicate;
import seedu.realodex.model.person.predicates.RemarkContainsKeyphrasePredicate;
import seedu.realodex.model.person.predicates.TagsMatchPredicate;
import seedu.realodex.model.tag.Tag;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_validArgsWithName_returnsFilterCommand() {
        String userInput = " n/Alice";
        FilterCommand expectedCommand = new FilterCommand(new NameContainsKeyphrasePredicate("Alice"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_invalidArgsWithName_throwsParseException() {
        assertParseFailure(parser, " n/#$@%^", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_validArgsWithRemark_returnsFilterCommand() {
        String userInput = " r/Loves cats";
        FilterCommand expectedCommand = new FilterCommand(new RemarkContainsKeyphrasePredicate("Loves cats"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_validArgsWithTag_returnsFilterCommand() {
        String userInput = " t/buyer";
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        FilterCommand expectedCommand = new FilterCommand(new TagsMatchPredicate(predicateTags));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_invalidArgsWithTag_throwsParseException() {
        String userInput = " t/customer";
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_validArgsWithTwoTags_returnsFilterCommand() {
        String userInput = " t/Buyer t/SELLER";
        Set<Tag> predicateTags = Set.of(new Tag("buyer"), new Tag("seller"));
        FilterCommand expectedCommand = new FilterCommand(new TagsMatchPredicate(predicateTags));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_oneValidOneInvalidTag_throwsParseException() {
        String userInput = " t/Buyer t/customer";
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }
    @Test
    void parse_validArgsWithThreeTags_throwsParseException() {
        String userInput = " t/Buyer t/Seller t/Buyer";
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));
    }

    @Test
    void parse_invalidTagFormat_throwsParseException() {
        String userInput = " t/Buyer$";
        assertThrows(IllegalArgumentException.class, () -> new Tag(userInput));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        String userInput = " invalidArg";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_invalidPrefix_throwsParseException() {
        String userInput = " p/999";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_multiplePrefixes_throwsParseException() {
        String userInput = " n/Alice r/Loves cats";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_FILTER_CONFLICT));
    }

    @Test
    void parse_multiplePrefixesWithEmptyRemark_throwsParseException() {
        String userInput = " n/Alice r/";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_FILTER_CONFLICT));
    }

    @Test
    void parse_multiplePrefixesWithEmptyName_throwsParseException() {
        String userInput = " n/ r/Loves cats";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_FILTER_CONFLICT));
    }
    @Test
    void parse_duplicatePrefixes_throwsParseException() {
        String userInput = " n/Alice n/Bob";
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }


    @Test
    void parse_emptyPreamble_throwsParseException() {
        String userInput = " yapyap n/Alice";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_invalidBirthMonthWithBirthday_throwsParseException() {
        assertParseFailure(parser, " n/#$@%^", Birthday.FILTER_MONTH_MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_validBirthMonthWithBirthday_returnsFilterCommand() {
        String userInput = " b/June";
        FilterCommand expectedCommand = new FilterCommand(new BirthdayIsInMonthPredicate("June"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
