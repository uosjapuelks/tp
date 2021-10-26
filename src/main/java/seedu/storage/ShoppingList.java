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
     * @param ingredient The ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient) {
        assert  ingredient != null : "Ingredient must not be null!";
        for (Ingredient ingredient1 : shoppingList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                ingredient1.addQuantity(1);
                return;
            }
        }

        shoppingList.add(ingredient);
    }

    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }
}
