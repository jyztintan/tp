package seedu.realodex.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.realodex.testutil.Assert.assertThrows;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.commands.AddCommand;
import seedu.realodex.logic.commands.ClearCommand;
import seedu.realodex.logic.commands.DeleteCommand;
import seedu.realodex.logic.commands.EditCommand;
import seedu.realodex.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.realodex.logic.commands.ExitCommand;
import seedu.realodex.logic.commands.FilterCommand;
import seedu.realodex.logic.commands.HelpCommand;
import seedu.realodex.logic.commands.ListCommand;
import seedu.realodex.logic.commands.SortCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.predicates.NameContainsKeyphrasePredicate;
import seedu.realodex.model.person.predicates.RemarkContainsKeyphrasePredicate;
import seedu.realodex.model.person.predicates.TagsMatchPredicate;
import seedu.realodex.model.tag.Tag;
import seedu.realodex.testutil.EditPersonDescriptorBuilder;
import seedu.realodex.testutil.PersonBuilder;
import seedu.realodex.testutil.PersonUtil;

public class RealodexParserTest {

    private final RealodexParser parser = new RealodexParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteByIndex() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteByName() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + "n/James");
        assertEquals(new DeleteCommand(new Name("James")), command);

        command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + "n/ Denzel Untrimmed ");
        assertEquals(new DeleteCommand(new Name("Denzel Untrimmed")), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);

        // command in caps
        EditCommand commandInCaps = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD.toUpperCase() + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), commandInCaps);

        // command mixed cases
        EditCommand commandMixedCases = (EditCommand) parser.parseCommand("eDiT" + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), commandMixedCases);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD.toLowerCase()) instanceof ExitCommand);
        assertTrue(parser.parseCommand("eXit") instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD.toLowerCase() + " 3") instanceof ExitCommand);
        assertTrue(parser.parseCommand("eXiT" + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filterByName() throws Exception {
        String keyphrase = "foo bar baz";
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " n/" + keyphrase);
        assertEquals(new FilterCommand(new NameContainsKeyphrasePredicate(keyphrase)), command);

        // command in caps
        FilterCommand commandInCaps = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD.toUpperCase() + " n/" + keyphrase);
        assertEquals(new FilterCommand(new NameContainsKeyphrasePredicate(keyphrase)), commandInCaps);

        // command mixed case
        FilterCommand commandMixedCase = (FilterCommand) parser.parseCommand(
                "fIlTeR" + " n/" + keyphrase);
        assertEquals(new FilterCommand(new NameContainsKeyphrasePredicate(keyphrase)), commandMixedCase);
    }

    @Test
    public void parseCommand_filterByRemark() throws Exception {
        String keyphrase = "foo bar baz";
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " r/" + keyphrase);
        assertEquals(new FilterCommand(new RemarkContainsKeyphrasePredicate(keyphrase)), command);

        // command in caps
        FilterCommand commandInCaps = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD.toUpperCase() + " n/" + keyphrase);
        assertEquals(new FilterCommand(new NameContainsKeyphrasePredicate(keyphrase)), commandInCaps);

        // command mixed case
        FilterCommand commandMixedCase = (FilterCommand) parser.parseCommand(
                "fIlTeR" + " n/" + keyphrase);
        assertEquals(new FilterCommand(new NameContainsKeyphrasePredicate(keyphrase)), commandMixedCase);
    }

    @Test
    public void parseCommand_filterByBuyerTag() throws Exception {
        Set<Tag> tagString = Set.of(new Tag("buyer"));
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " t/buyer ");
        assertEquals(new FilterCommand(new TagsMatchPredicate(tagString)), command);
    }

    @Test
    public void parseCommand_filterBySellerTags() throws Exception {
        Set<Tag> tagString = Set.of(new Tag("seller"));
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " t/seller");
        assertEquals(new FilterCommand(new TagsMatchPredicate(tagString)), command);
    }
    @Test
    public void parseCommand_filterByBuyerAndSellerTags() throws Exception {
        Set<Tag> tagString = Set.of(new Tag("buyer"), new Tag("seller"));
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " t/buyer " + "t/seller");
        assertEquals(new FilterCommand(new TagsMatchPredicate(tagString)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD.toUpperCase()) instanceof HelpCommand);
        assertTrue(parser.parseCommand("hElP") instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD.toUpperCase() + " 3") instanceof HelpCommand);
        assertTrue(parser.parseCommand("HeLp" + " 3") instanceof HelpCommand);

    }

    @Test
    public void parseCommand_add_help() throws Exception {
        assertTrue(parser.parseCommand("add help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("ADD HELP") instanceof HelpCommand);
        assertTrue(parser.parseCommand("aDd hElP") instanceof HelpCommand);
        HelpCommand expected = new HelpCommand("add");
        assertEquals(parser.parseCommand("add help"), expected);
        assertEquals(parser.parseCommand("ADD HELP"), expected);
        assertEquals(parser.parseCommand("aDd hElP"), expected);

    }

    @Test
    public void parseCommand_clear_help() throws Exception {
        assertTrue(parser.parseCommand("clearRealodex help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("clearRealodex help".toUpperCase()) instanceof HelpCommand);
        assertTrue(parser.parseCommand("clearRealodex help".toLowerCase()) instanceof HelpCommand);
        HelpCommand expected = new HelpCommand("clearRealodex");
        assertEquals(parser.parseCommand("clearRealodex help"), expected);
        assertEquals(parser.parseCommand("clearRealodex help".toUpperCase()), expected);
        assertEquals(parser.parseCommand("clearRealodex help".toLowerCase()), expected);
    }

    @Test
    public void parseCommand_delete_help() throws Exception {
        assertTrue(parser.parseCommand("delete help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("DELETE HELP") instanceof HelpCommand);
        assertTrue(parser.parseCommand("dElEtE hElP") instanceof HelpCommand);
        HelpCommand expected = new HelpCommand("delete");
        assertEquals(parser.parseCommand("delete help"), expected);
        assertEquals(parser.parseCommand("DELETE HELP"), expected);
        assertEquals(parser.parseCommand("dElEtE hElP"), expected);

    }

    @Test
    public void parseCommand_edit_help() throws Exception {
        assertTrue(parser.parseCommand("edit help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("edit help".toUpperCase()) instanceof HelpCommand);
        assertTrue(parser.parseCommand("eDit hElP") instanceof HelpCommand);
        HelpCommand expected = new HelpCommand("edit");
        assertEquals(parser.parseCommand("edit help"), expected);
        assertEquals(parser.parseCommand("edit help".toUpperCase()), expected);
        assertEquals(parser.parseCommand("eDit HelP"), expected);
    }

    @Test
    public void parseCommand_filter_help() throws Exception {
        assertTrue(parser.parseCommand("filter help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("filter help".toUpperCase()) instanceof HelpCommand);
        assertTrue(parser.parseCommand("fIlTeR hElP") instanceof HelpCommand);
        HelpCommand expected = new HelpCommand("filter");
        assertEquals(parser.parseCommand("filter help"), expected);
        assertEquals(parser.parseCommand("filter help".toUpperCase()), expected);
        assertEquals(parser.parseCommand("filTer hElP"), expected);

    }

    @Test
    public void parseCommand_list_help() throws Exception {
        assertTrue(parser.parseCommand("list help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("list help".toUpperCase()) instanceof HelpCommand);
        assertTrue(parser.parseCommand("liSt hElP") instanceof HelpCommand);
        HelpCommand expected = new HelpCommand("list");
        assertEquals(parser.parseCommand("list help"), expected);
        assertEquals(parser.parseCommand("list help".toUpperCase()), expected);
        assertEquals(parser.parseCommand("liSt hELP"), expected);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD.toUpperCase()) instanceof ListCommand);
        assertTrue(parser.parseCommand("lIsT") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD.toUpperCase() + " 3") instanceof ListCommand);
        assertTrue(parser.parseCommand("lIsT" + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort_help() throws Exception {
        assertTrue(parser.parseCommand("sort help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("sort help".toUpperCase()) instanceof HelpCommand);
        assertTrue(parser.parseCommand("sOrT HelP") instanceof HelpCommand);
        HelpCommand expected = new HelpCommand("sort");
        assertEquals(parser.parseCommand("sort help"), expected);
        assertEquals(parser.parseCommand("sort help".toUpperCase()), expected);
        assertEquals(parser.parseCommand("soRt hElP"), expected);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD.toUpperCase()) instanceof SortCommand);
        assertTrue(parser.parseCommand("soRt") instanceof SortCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
