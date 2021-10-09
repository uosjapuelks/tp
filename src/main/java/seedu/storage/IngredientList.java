package seedu.storage;

import seedu.data.ingredient.Ingredient;

import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> ingredientList;

    public IngredientList() {
        this.ingredientList = new ArrayList<Ingredient>();
    }

    /**
     * Adds an ingredient to ingredientList.
     *
     * @param ingredient The Ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredientList.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredientList.remove(ingredient);
    }

    /**
     * Sorts the ingredients in ingredientList by date if byDate is true and by description if false.
     *
     * @param byDate toggle to sort by name or expiry.
     * @return sorted ingredient ArrayList.
     */
    public ArrayList<Ingredient> sortIngredient(boolean byDate) {
        ArrayList<Ingredient> sortedList = new ArrayList<Ingredient>(ingredientList);
        sortedList.sort((byDate ? Ingredient.IngExpiryComparator : Ingredient.IngNameComparator));
        return sortedList;
    }

    /**
     * Returns the current list of ingredients.
     *
     * @return List of ingredients.
     */
    public ArrayList<Ingredient> getIngredientList(String sortType) {
        switch (sortType.toLowerCase()) {
        case "e":
            return sortIngredient(true);
        case "default":
            return sortIngredient(false);
        default:
            return ingredientList;
        /*TODO default throw exception*/
        }
    }

    /**
     * Returns an Array List of Ingredients with names containing the search term.
     *
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
        ingredientList = new ArrayList<Ingredient>();
    }
}
