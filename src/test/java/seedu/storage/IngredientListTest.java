package seedu.storage;

import org.junit.jupiter.api.Test;
import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class IngredientListTest {
    private final LocalDate today = LocalDate.now();
    private final Ingredient itemAExpireTomorrow = new Ingredient("aaa", today.plusDays(1));
    private final Ingredient itemBExpireToday = new Ingredient("bbb", today);
    private final Ingredient itemCExpireYesterday = new Ingredient("ccc", today.minusDays(1));
    private final IngredientList testList = new IngredientList();
    private final ArrayList<Ingredient> expectedList = new ArrayList<Ingredient>();

    private void fillExpectedList(Ingredient a, Ingredient b, Ingredient c) {
        expectedList.add(a);
        expectedList.add(b);
        expectedList.add(c);
    }

    private IngredientList filledTestList() {
        testList.addIngredient(itemCExpireYesterday);
        testList.addIngredient(itemAExpireTomorrow);
        testList.addIngredient(itemBExpireToday);
        return testList;
    }

    @Test
    void containsIngredient_aInTestList_expectedResultTrue() {
        testList.addIngredient(itemAExpireTomorrow);
        assertTrue(testList.containsIngredient(itemAExpireTomorrow));
    }

    @Test
    void containsIngredient_aNotInTestList_expectedResultTrue() {
        testList.addIngredient(itemCExpireYesterday);
        assertFalse(testList.containsIngredient(itemAExpireTomorrow));
    }

    @Test
    void sortIngredient_sortByDate_expectCToBToA() throws FridgetException {
        fillExpectedList(itemCExpireYesterday, itemBExpireToday, itemAExpireTomorrow);
        try {
            assertEquals(expectedList, filledTestList().sortIngredient(true));
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void sortIngredient_sortByName_expectAToBToC() throws FridgetException {
        fillExpectedList(itemAExpireTomorrow, itemBExpireToday, itemCExpireYesterday);
        try {
            assertEquals(expectedList, filledTestList().sortIngredient(false));
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void getIngredient_CtoAtoBInput_expectCToAToBOutput() {
        fillExpectedList(itemCExpireYesterday, itemAExpireTomorrow, itemBExpireToday);
        try {
            assertEquals(expectedList, filledTestList().getIngredientList("r"));
        } catch (FridgetException e) {
            fail();
        }
    }
}
