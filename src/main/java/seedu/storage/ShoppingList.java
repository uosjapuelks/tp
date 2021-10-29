package seedu.storage;

import seedu.data.ingredient.Ingredient;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> shoppingList;

    /**
     * Constructor for ShoppingList.
     */
    public ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    /**
     * Adds an ingredient into shoppingList.
     *
     * @param ingredient The ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient, int quantity) {
        assert  ingredient != null : "Ingredient must not be null!";
        for (Ingredient ingredient1 : shoppingList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                ingredient1.addQuantity(quantity);
                return;
            }
        }

        shoppingList.add(ingredient);
    }

    /**
     * Removes an ingredient from the shopping list.
     *
     * @param ingredient Ingredient to be removed.
     * @param quantity Quantity to be removed.
     */
    public void removeIngredient(Ingredient ingredient, int quantity) {
        assert ingredient != null : "Ingredient must not be null!";
        assert quantity != 0 : "Quantity removed must no be 0!";

        for (Ingredient ingredient1 : shoppingList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                if (quantity >= ingredient1.getQuantity()) {
                    shoppingList.remove(ingredient1);
                    return;
                }
                ingredient1.removeQuantity(quantity);
            }
        }
    }

    /**
     * Checks if an item with the same name exists in the shopping list.
     *
     * @param ingredient Ingredient to be searched.
     * @return Boolean value of true if exist.
     */
    public boolean searchIngredientNameExist(Ingredient ingredient) {
        for (Ingredient ingredient1 : shoppingList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the current shopping list.
     *
     * @return List of ingredients in the shopping list.
     */
    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }

    /**
     * Resets the shopping list.
     */
    public void resetList() {
        shoppingList = new ArrayList<Ingredient>();
    }
}
