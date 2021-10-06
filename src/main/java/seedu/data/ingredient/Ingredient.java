package seedu.data.ingredient;

import java.time.LocalDate;

public class Ingredient {
    protected String ingredientName;
    protected LocalDate expiryDate;

    /**
     * Constructor for Ingredient.
     * @param ingredientName name of the ingredient.
     * @param expiryDate date of expiry for ingredient.
     */
    public Ingredient(String ingredientName, LocalDate expiryDate) {
        this.ingredientName = ingredientName;
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return ingredientName + " | " + expiryDate;
    }
}
