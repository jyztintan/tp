package seedu.realodex.testutil;

import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_HOUSINGTYPE_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_HOUSINGTYPE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_INCOME_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_AMY_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_BOB_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withIncome("10000")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withFamily("4")
            .withTags("buyer")
            .withHousingType("HDB")
            .withRemark("this is a remark")
            .withBirthday("3Jun2003").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withIncome("20000")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withFamily("4")
            .withHousingType("HDB")
            .withTags("seller", "buyer").withBirthday("3Jun2003").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withIncome("30000")
            .withEmail("heinz@example.com").withAddress("wall street").withTags("buyer")
            .withHousingType("HDB")
            .withRemark("Carl was supposed to start with a K, but the doctor misspelled when he was born")
            .withBirthday("6Jun2006").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withIncome("40000")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("buyer")
            .withHousingType("HDB").withRemark("White VANS").withBirthday("25Dec1999").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withIncome("50000")
            .withEmail("werner@example.com").withAddress("michegan ave").withTags("buyer")
            .withHousingType("HDB").withBirthday("1Jan2004").build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withIncome("60000")
            .withEmail("lydia@example.com").withAddress("little tokyo").withTags("seller")
            .withHousingType("HDB").withBirthday("3Jun2003").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withIncome("70000")
            .withEmail("anna@example.com").withAddress("4th street").withTags("buyer")
            .withHousingType("HDB").withRemark("Fun fact: George's brother's name is \"The\".")
            .withBirthday("23Apr1983").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withTags("buyer").withHousingType("HDB")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withTags("buyer").withHousingType("HDB").withBirthday("23Apr1983").build();

    public static final Person HENRY = new PersonBuilder().withName("Henry Johnson").withPhone("9482541")
            .withIncome("80000").withEmail("john@example.com").withAddress("5th avenue").withTags("seller")
            .withHousingType("HDB").withBirthday("12Mar1995").build();

    public static final Person ISABELLA = new PersonBuilder().withName("Isabella Lee").withPhone("9482682")
            .withIncome("90000").withEmail("isabella@example.com").withAddress("main street").withTags("buyer")
            .withHousingType("HDB").withBirthday("5Sep1990").build();

    public static final Person JACKSON = new PersonBuilder().withName("Jackson Wang").withPhone("9482335")
            .withIncome("100000").withEmail("jackson@example.com").withAddress("baker street").withTags("seller")
            .withHousingType("HDB").withBirthday("8Jul1988").build();

    public static final Person KATHERINE = new PersonBuilder().withName("Katherine Smith").withPhone("9482774")
            .withIncome("110000").withEmail("katherine@example.com").withAddress("park avenue").withTags("buyer")
            .withHousingType("HDB").withBirthday("16Nov1984").build();

    public static final Person LUCAS = new PersonBuilder().withName("Lucas Brown").withPhone("9482993")
            .withIncome("120000").withEmail("lucas@example.com").withAddress("ocean drive").withTags("seller")
            .withHousingType("HDB").withBirthday("20Jan1980").build();

    public static final Person MADELINE = new PersonBuilder().withName("Madeline Garcia").withPhone("9482662")
            .withIncome("130000").withEmail("madeline@example.com").withAddress("wallace street").withTags("buyer")
            .withHousingType("HDB").withBirthday("9Apr1976").build();

    public static final Person NATHANIEL = new PersonBuilder().withName("Nathaniel Martinez").withPhone("9482891")
            .withIncome("140000").withEmail("nathaniel@example.com").withAddress("elm street").withTags("seller")
            .withHousingType("HDB").withBirthday("14Aug1972").build();

    public static final Person OLIVIA = new PersonBuilder().withName("Olivia Taylor").withPhone("9482889")
            .withIncome("150000").withEmail("olivia@example.com").withAddress("maple street").withTags("buyer")
            .withHousingType("HDB").withBirthday("17Dec1968").build();





    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY_NAME_CAPS = new PersonBuilder().withName(VALID_NAME_AMY_FIRST_LETTER_CAPS)
            .withPhone(VALID_PHONE_AMY)
            .withIncome(VALID_INCOME_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withFamily(VALID_FAMILY_AMY)
            .withTags(VALID_TAG_AMY)
            .withHousingType(VALID_HOUSINGTYPE_AMY)
            .withBirthday(VALID_BIRTHDAY_AMY)
            .build();

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB_FIRST_LETTER_CAPS)
            .withPhone(VALID_PHONE_BOB)
            .withIncome(VALID_INCOME_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withFamily(VALID_FAMILY_BOB)
            .withTags(VALID_TAG_BOB)
            .withHousingType(VALID_HOUSINGTYPE_BOB)
            .withRemark(VALID_REMARK_BOB)
            .withBirthday(VALID_BIRTHDAY_BOB)
            .build();

    public static final String KEYPHRASE_MATCHING_MEIER = "Meier"; // A keyphrase that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Realodex} with all the typical persons.
     */
    public static Realodex getTypicalRealodex() {
        Realodex ab = new Realodex();
        for (Person person : getFirstTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code Realodex} with another typical persons list.
     */
    public static Realodex getSecondTypicalRealodex() {
        Realodex ab = new Realodex();
        for (Person person : getSecondTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getFirstTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getSecondTypicalPersons() {
        return new ArrayList<>(Arrays.asList(HENRY, ISABELLA, JACKSON , KATHERINE, LUCAS, MADELINE , NATHANIEL));
    }
}
