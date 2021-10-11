package seedu.storage;

import seedu.data.exception.FridgetException;
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
        for (Ingredient ingredient1 : ingredientList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())
                    && ingredient1.getExpiryDate().equals(ingredient.getExpiryDate())) {
                ingredient1.addQuantity(1);
                return;
            }
        }

        this.ingredientList.add(ingredient);
    }

    /**
     * Removes an ingredient from the ingredient list.
     * @param ingredient Ingredient to be removed.
     */
    public void removeIngredient(Ingredient ingredient, int qty) {
        if (ingredient.getQuantity() == qty) {
            ingredientList.remove(ingredient);
            return;
        }
        ingredient.removeQuantity(qty);
    }

    public boolean containsIngredient(Ingredient ingredient) {
        return ingredientList.contains(ingredient);
    }

    /**
     * Sorts the ingredients in ingredientList by date if byDate is true and by description if false.
     *
     * @param byDate toggle to sort by name or expiry.
     * @return sorted ingredient ArrayList.
     */
    public ArrayList<Ingredient> sortIngredient(boolean byDate) throws FridgetException{
        if (ingredientList.isEmpty()) {
            String emptyListMessage = "You currently have nothing in your fridge.\n"
                    + "Input \"help\" to get started!";
            throw new FridgetException(emptyListMessage);
        }
        ArrayList<Ingredient> sortedList = new ArrayList<Ingredient>(ingredientList);
        sortedList.sort((byDate ? Ingredient.IngExpiryComparator : Ingredient.IngNameComparator));
        return sortedList;
    }

    /**
     * Returns the current list of ingredients.
     *
     * @return List of ingredients.
     */
    public ArrayList<Ingredient> getIngredientList(String sortType) throws FridgetException {
        switch (sortType.toLowerCase()) {
        case "e":
            return sortIngredient(true);
        case "default":
            return sortIngredient(false);
        case "r":
            return ingredientList;
        default:
            throw new FridgetException("Unrecognisable list command. Try: <list -e>");
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
