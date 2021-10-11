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
    private final LocalDate TODAY = LocalDate.now();
    private final Ingredient A = new Ingredient("aaa", TODAY.plusDays(1));
    private final Ingredient B = new Ingredient("bbb", TODAY);
    private final Ingredient C = new Ingredient("ccc", TODAY.minusDays(1));
    private final IngredientList TEST_LIST = new IngredientList();
    private final ArrayList<Ingredient> EXPECTED_LIST = new ArrayList<Ingredient>();
    private void fillExpectedList(Ingredient a,Ingredient b, Ingredient c) {
        EXPECTED_LIST.add(a);
        EXPECTED_LIST.add(b);
        EXPECTED_LIST.add(c);
    }
    private IngredientList filledTestList() {
        TEST_LIST.addIngredient(C);
        TEST_LIST.addIngredient(A);
        TEST_LIST.addIngredient(B);
        return TEST_LIST;
    }

    @Test

    void containsIngredient_aInTestList_expectedResultTrue() {
        TEST_LIST.addIngredient(A);
        assertTrue(TEST_LIST.containsIngredient(A));
    }

    @Test
    void containsIngredient_aNotInTestList_expectedResultTrue() {
        TEST_LIST.addIngredient(C);
        assertFalse(TEST_LIST.containsIngredient(A));
    }

    @Test
    void sortIngredient_sortByDate_expectCBA() {
        fillExpectedList(C,B,A);
        assertEquals(EXPECTED_LIST, filledTestList().sortIngredient(true));
    }

    @Test
    void sortIngredient_sortByName_expectABC() {
        fillExpectedList(A,B,C);
        assertEquals(EXPECTED_LIST, filledTestList().sortIngredient(false));
    }

    @Test
    void getIngredient_CABInput_expectCABOutput() {
        fillExpectedList(C, A, B);
        try {
            assertEquals(EXPECTED_LIST, filledTestList().getIngredientList("r"));
        } catch (FridgetException e) {
            fail();
        }
    }
}
