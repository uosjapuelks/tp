package seedu.storage;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

import java.util.ArrayList;

public class IngredientList {
    private ArrayList<Ingredient> ingredientList;

    /**
     * Constructor for IngredientList.
     */
    public IngredientList() {
        this.ingredientList = new ArrayList<Ingredient>();
    }

    /**
     * Adds an ingredient into ingredientList.
     *
     * @param ingredient The Ingredient to be added.
     * @return Updated quantity of the ingredient added in the list.
     */
    public int addIngredient(Ingredient ingredient) {
        assert ingredient != null : "Ingredient must not be null!";
        for (Ingredient ingredient1 : ingredientList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())
                    && ingredient1.getExpiryDate().equals(ingredient.getExpiryDate())) {
                ingredient1.addQuantity(1);
                return ingredient1.getQuantity();
            }
        }

        ingredientList.add(ingredient);
        return 1;
    }

    /**
     * Removes an ingredient from the ingredient list.
     *
     * @param ingredient Ingredient to be removed.
     * @param qty Quantity to be removed.
     * @return Returns true if ingredient is removed from the list (qty = 0)
     */
    public boolean removeIngredient(Ingredient ingredient, int qty) {
        assert ingredient != null : "Ingredient must not be null!";
        assert qty != 0 : "Quantity removed must not be 0!";
        if (ingredient.getQuantity() == qty) {
            ingredientList.remove(ingredient);
            return !searchIngredientNameExist(ingredient);
        }
        ingredient.removeQuantity(qty);
        return !searchIngredientNameExist(ingredient);
    }

    /**
     * Checks if an item with the same name exists in the ingredient list.
     *
     * @param ingredient Ingredient to be searched.
     * @return Boolean value of true if exist.
     */
    public boolean searchIngredientNameExist(Ingredient ingredient) {
        for (Ingredient ingredient1 : ingredientList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Overwrite the quantity of an item to the new amount.
     *
     * @param targetIngredient Item in which quantity is to be overwritten.
     * @param toQuantity Overwrite to this quantity.
     */
    public void updateQuantity(Ingredient targetIngredient, int toQuantity) {
        targetIngredient.setQuantity(toQuantity);
    }

    /**
     * Checks if ingredientList contains an Ingredient.
     *
     * @param ingredient an ingredient of type Ingredient.
     * @return true if ingredient is in the ingredientList.
     */
    public boolean containsIngredient(Ingredient ingredient) {
        assert ingredient != null : "Ingredient must not be null!";
        return ingredientList.contains(ingredient);
    }

    /**
     * Sorts the ingredients in ingredientList by date if byDate is true and by description if false.
     *
     * @param byDate toggle to sort by name or expiry.
     * @return sorted ingredient ArrayList.
     * @throws FridgetException thrown when there are no items in ingredientList.
     */
    public ArrayList<Ingredient> sortIngredient(boolean byDate) throws FridgetException {
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
     * @throws FridgetException thrown when String after list is unrecognised.
     */
    public ArrayList<Ingredient> getIngredientList(String sortType) throws FridgetException {
        assert sortType != null : "Sort type must not be null!";
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
     * @throws FridgetException thrown when there are no items in ingredientList.
     */
    public ArrayList<Ingredient> findAllMatchingIngredients(String searchTerm) throws FridgetException {
        assert searchTerm != null : "Search term must not be null!";
        if (ingredientList.isEmpty()) {
            String emptyListMessage = "You currently have nothing in your fridge.\n"
                    + "Input \"help\" to get started!";
            throw new FridgetException(emptyListMessage);
        }
        ArrayList<Ingredient> matchingIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getIngredientName().toLowerCase().contains(searchTerm.toLowerCase())) {
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
