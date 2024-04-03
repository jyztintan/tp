package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void toStringWithRepresentation() {
        HousingType housingType1 = new HousingType("HDB");
        assertEquals(housingType1.toStringWithRepresentation(), "Preferred housing type is HDB");

        HousingType housingType2 = new HousingType("CONDOMINIUM");
        assertEquals(housingType2.toStringWithRepresentation(), "Preferred housing type is CONDOMINIUM");

        HousingType housingType3 = new HousingType("LANDED PROPERTY");
        assertEquals(housingType3.toStringWithRepresentation(), "Preferred housing type is LANDED PROPERTY");

        HousingType housingType4 = new HousingType("GOOD CLASS BUNGALOW");
        assertEquals(housingType4.toStringWithRepresentation(), "Preferred housing type is GOOD CLASS BUNGALOW");
    }

    @Test
    public void equals() {
        HousingType housingType1 = new HousingType("HDB");
        HousingType housingType2 = new HousingType("HDB");
        HousingType housingType3 = new HousingType("CONDOMINIUM");

        // same object -> returns true
        assertTrue(housingType1.equals(housingType1));

        // same values -> returns true
        assertTrue(housingType1.equals(housingType2));

        // different types -> returns false
        assertFalse(housingType1.equals(1));

        // different values -> returns false
        assertFalse(housingType1.equals(housingType3));
    }

    @Test
    public void hashCodeTest() {
        HousingType housingType1 = new HousingType("HDB");
        HousingType housingType2 = new HousingType("HDB");
        HousingType housingType3 = new HousingType("CONDOMINIUM");

        // same object -> returns true
        assertTrue(housingType1.hashCode() == housingType1.hashCode());

        // same values -> returns true
        assertTrue(housingType1.hashCode() == housingType2.hashCode());

        // different values -> returns false
        assertFalse(housingType1.hashCode() == housingType3.hashCode());
    }

}
