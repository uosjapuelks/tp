package seedu.storage;

import seedu.data.ingredient.Ingredient;

import java.util.ArrayList;

public class ShoppingList {
    protected ArrayList<Ingredient> shoppingList;

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
     * Returns the current shopping list.
     *
     * @return List of ingredients in the shopping list.
     */
    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }
}
