package seedu.storage;

import seedu.data.ingredient.Ingredient;
import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> ingredientList;

    public IngredientList(ArrayList<Ingredient> ingredientList) {
        ingredientList = new ArrayList<>();
    }
}
