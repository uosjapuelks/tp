package seedu.storage;

import seedu.data.ingredient.Ingredient;
import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> ingredientList;

    public IngredientList() {
        ingredientList = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }
}
