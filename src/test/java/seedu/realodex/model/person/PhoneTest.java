package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.tag.Tag;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
        String invalidPhone2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone2));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        Phone phone = new Phone("999");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("995")));
    }

    public static class TagTest {

        @Test
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Tag(null));
        }

        @Test
        public void constructor_invalidTagName_throwsIllegalArgumentException() {
            String invalidTagName = "";
            assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
        }

        @Test
        public void isValidTagName() {
            // null tag name
            assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

            // Invalid tag names
            assertFalse(Tag.isValidTagName("")); // Empty tag name
            assertFalse(Tag.isValidTagName("Buyers")); // Should not be plural
            assertFalse(Tag.isValidTagName("Sellers ")); // Extra space at the end
            assertFalse(Tag.isValidTagName("Invalid Tag")); // Space within the tag name

            // Valid tag names
            assertTrue(Tag.isValidTagName("buyer"));
            assertTrue(Tag.isValidTagName("seller"));
            assertTrue(Tag.isValidTagName("buYeR")); // Tag converts string to uppercase
            assertTrue(Tag.isValidTagName("seLLer")); // Tag converts string to uppercase
        }

    }
}
