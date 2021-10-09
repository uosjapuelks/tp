package seedu.data.ingredient;

import java.time.LocalDate;
import java.util.Comparator;

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

    /**
     * Gets the Ingredient's name.
     * @return Ingredient's name.
     */
    public String getIngredientName() {
        return ingredientName;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Comparator that compares Strings of description of ingredients.
     */
    public static Comparator<Ingredient> IngNameComparator = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient i1, Ingredient i2) {

            String ingredient1 = i1.getIngredientName().toLowerCase();
            String ingredient2 = i2.getIngredientName().toLowerCase();

            //ascending order
            return ingredient1.compareTo(ingredient2);
        }
    };

    /**
     * Comparator that compares LocalDates of the ingredients.
     */
    public static Comparator<Ingredient> IngExpiryComparator = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient i1, Ingredient i2) {

            LocalDate ingredientExpiry1 = i1.getExpiryDate();
            LocalDate ingredientExpiry2 = i2.getExpiryDate();

            //Ascending order
            return ingredientExpiry1.compareTo(ingredientExpiry2);
        }
    };
}
