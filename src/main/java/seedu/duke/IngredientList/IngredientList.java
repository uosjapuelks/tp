package seedu.duke.IngredientList;

import seedu.duke.Data.Ingredient.Ingredient;
import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> ingredientList;

    public IngredientList(ArrayList<Ingredient> ingredientList) {
        ingredientList = new ArrayList<>(100);
    }
}
