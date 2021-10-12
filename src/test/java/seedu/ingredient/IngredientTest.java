package seedu.ingredient;

import org.junit.jupiter.api.Test;
import seedu.data.ingredient.Ingredient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    public static final String DUMMY_NAME = "dummyIngredient";
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    public static final LocalDate FAR_FUTURE = LocalDate.now().plusYears(5);
    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

    @Test
    void isNearExpiry_expiringDummyIngredient_expectTrue() {
        Ingredient expiringDummy = new Ingredient(DUMMY_NAME, TOMORROW);
        assertTrue(expiringDummy.isNearExpiry());
    }

    @Test
    void isNearExpiry_notExpiringDummyIngredient_expectFalse() {
        Ingredient expiringDummy = new Ingredient(DUMMY_NAME, FAR_FUTURE);
        assertFalse(expiringDummy.isNearExpiry());
    }

    @Test
    void isPastExpiry_expiringDummyIngredient_expectFalse() {
        Ingredient expiringDummy = new Ingredient(DUMMY_NAME, TOMORROW);
        assertFalse(expiringDummy.isPastExpiry());
    }

    @Test
    void isPastExpiry_expiredDummyIngredient_expectTrue() {
        Ingredient expiringDummy = new Ingredient(DUMMY_NAME, YESTERDAY);
        assertTrue(expiringDummy.isPastExpiry());
    }

    @Test
    void getColoredExpiryDate_ExpiringIngredient_expectOrangeFormattedExpiry() {
        Ingredient expiringDummy = new Ingredient(DUMMY_NAME, TOMORROW);
        String date = TOMORROW.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        // String expected = String.format("\u001B[33m%s\u001B[0m", date);
        String expected = date;
        assertEquals(expected, expiringDummy.getColoredExpiryDate());
    }

    @Test
    void toString_expiredDummyIngredient_expectCorrectStringFormat() {
        LocalDate expiredDate = LocalDate.parse("1212-12-12");
        Ingredient expiredDummy = new Ingredient(DUMMY_NAME, expiredDate);
        // assertEquals("dummyIngredient | Qty: 1 | \u001B[31m12 Dec 1212\u001B[0m", expiredDummy.toString());
        assertEquals("dummyIngredient | Qty: 1 | 12 Dec 1212", expiredDummy.toString());
    }

    @Test
    void saveFormat_dummyIngredient_expectCorrectSaveFormat() {
        Ingredient dummy = new Ingredient(DUMMY_NAME, YESTERDAY);
        String correctOutput = "dummyIngredient | Qty: 1 | " + YESTERDAY;
        assertEquals(correctOutput, dummy.saveFormat());
    }

    @Test
    void ingNameComparator_ingredientAB_expectAscendingTrue() {
        Ingredient a = new Ingredient("A", TOMORROW);
        Ingredient b = new Ingredient("B", YESTERDAY);
        int expected = "A".compareTo("B");
        int actual = Ingredient.IngNameComparator.compare(a, b);
        assertEquals(expected, actual);
    }

    @Test
    void ingExpiryComparator_compareFromAToB_expectDescendingTrue() {
        Ingredient a = new Ingredient("A", TOMORROW);
        Ingredient b = new Ingredient("B", YESTERDAY);
        int expected = TOMORROW.compareTo(YESTERDAY);
        int actual = Ingredient.IngExpiryComparator.compare(a, b);
        assertEquals(expected, actual);
    }
}
