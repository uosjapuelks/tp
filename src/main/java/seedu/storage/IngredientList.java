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

    /**
     * Returns the current list of ingredients.
     * @return List of ingredients.
     */
    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    /**
     * Returns an Array List of Ingredients with names containing the search term.
     * @param searchTerm The search term used to find matching Ingredients.
     */
    public ArrayList<Ingredient> findAllMatchingIngredients(String searchTerm) {
        ArrayList<Ingredient> matchingIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getIngredientName().contains(searchTerm)) {
                matchingIngredients.add(ingredient);
            }
        }
        return matchingIngredients;
    }

    /**
     * Resets the Array List of ingredients.
     */
    public void resetList() {
        ingredientList.removeAll(ingredientList);
    }
}
