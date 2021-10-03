package seedu.duke.Data.Ingredient;

import java.util.Date;

public abstract class Ingredient {
    protected String ingredientName;
    protected Date expiryDate;

    /**
     * Constructor for Ingredient.
     * @param ingredientName name of the ingredient.
     * @param expiryDate date of expiry for ingredient.
     */
    public Ingredient(String ingredientName, Date expiryDate) {
        this.ingredientName = ingredientName;
        this.expiryDate = expiryDate;
    }
}
