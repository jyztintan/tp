package seedu.realodex.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_HOUSINGTYPE_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_HOUSINGTYPE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_AMY_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_AMY_VARYING_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_BOB_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.realodex.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realodex.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import org.junit.jupiter.api.Test;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.realodex.model.Model;
import seedu.realodex.model.ModelManager;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.UserPrefs;
import seedu.realodex.model.person.Person;
import seedu.realodex.testutil.EditPersonDescriptorBuilder;
import seedu.realodex.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalRealodex(), new UserPrefs());


    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB_FIRST_LETTER_CAPS).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_AMY).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB_FIRST_LETTER_CAPS)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_AMY).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .withName(VALID_NAME_BOB_FIRST_LETTER_CAPS).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB_FIRST_LETTER_CAPS).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in realodex
        Person personInList = model.getRealodex().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(
                VALID_NAME_BOB_FIRST_LETTER_CAPS).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of realodex
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of realodex list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRealodex().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB_FIRST_LETTER_CAPS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editName_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_AMY_FIRST_LETTER_CAPS).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY_FIRST_LETTER_CAPS).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editPhone_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withPhone(VALID_PHONE_AMY).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editAddress_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withAddress(VALID_ADDRESS_AMY).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editEmail_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withEmail(VALID_EMAIL_AMY).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editFamily_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        //edit from 4 to 5
        Person editedPerson = personInList.withFamily("5").build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withFamily("5").build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editTags_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        //edit from buyer to seller
        Person editedPerson = personInList.withTags(VALID_TAG_BOB).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags(VALID_TAG_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editHousingType_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        //edit from hdb to condo
        Person editedPerson = personInList.withHousingType(VALID_HOUSINGTYPE_BOB).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withHousingType(VALID_HOUSINGTYPE_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editRemark_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withRemark(VALID_REMARK_AMY).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withRemark(VALID_REMARK_AMY).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editBirthday_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withBirthday(VALID_BIRTHDAY_AMY).build();
        EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withBirthday(VALID_BIRTHDAY_AMY).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        Model expectedModel = new ModelManager(new Realodex(model.getRealodex()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(index, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
