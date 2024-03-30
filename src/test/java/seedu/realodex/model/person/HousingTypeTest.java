package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HousingTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HousingType(null));
    }

    @Test
    public void constructor_invalidHousingType_throwsIllegalArgumentException() {
        String invalidHousingType = "";
        assertThrows(IllegalArgumentException.class, () -> new HousingType(invalidHousingType));
    }

    @Test
    public void isValidHousingType() {
        // null housing type
        assertThrows(NullPointerException.class, () -> HousingType.isValidHousingType(null));

        // Invalid housing types
        assertFalse(HousingType.isValidHousingType("")); // Empty tag name
        assertFalse(HousingType.isValidHousingType("HDBB")); // Does not match any housing type
        assertFalse(HousingType.isValidHousingType("HDB ")); // Extra space at the end
        assertFalse(HousingType.isValidHousingType("H DB")); // Space within the tag name

        // Valid housing types
        assertTrue(HousingType.isValidHousingType("hdb"));
        assertTrue(HousingType.isValidHousingType("CONDOMINIUM"));
        assertTrue(HousingType.isValidHousingType("landed property")); // Tag converts string to uppercase
        assertTrue(HousingType.isValidHousingType("good class bungalow")); // Tag converts string to uppercase
    }

}
