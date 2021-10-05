package seedu.storage;

import seedu.data.ingredient.Ingredient;
import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> ingredientList;

    public IngredientList() {
        ingredientList = new ArrayList<Ingredient>();
    }

    /**
     * Adds an ingredient to ingredientList.
     * @param ingredient The Ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }
}
