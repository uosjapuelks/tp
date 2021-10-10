package seedu.data.ingredient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class Ingredient {
    protected String ingredientName;
    protected LocalDate expiryDate;

    /**
     * Constructor for Ingredient.
     *
     * @param ingredientName name of the ingredient.
     * @param expiryDate     date of expiry for ingredient.
     */
    public Ingredient(String ingredientName, LocalDate expiryDate) {
        this.ingredientName = ingredientName;
        this.expiryDate = expiryDate;
    }

    /**
     * String is represented as name + Expiry date that is colored depending on expiry status.
     *
     * @return Ingredient Name and Expiry that is colored.
     */
    @Override
    public String toString() {
        String expiry = expiryDate.format((DateTimeFormatter.ofPattern("dd MMM yyyy")));
        String decidedColor = decideColor();
        String coloredExpiry = String.format(decidedColor, expiry);
        return ingredientName + " | " + coloredExpiry;
    }

    /**
     * Choose color depending on expiry status.
     *
     * @return Red for expired, orange for nearing expiry, and green otherwise.
     */
    private String decideColor() {
        String red = "\u001B[31m%s\u001B[0m";
        String orange = "\u001B[33m%s\u001B[0m";
        String green = "\u001B[32m%s\u001B[0m";

        String decidedColor;
        if (isPastExpiry()) {
            decidedColor = red;
        } else if (isNearExpiry()) {
            decidedColor = orange;
        } else {
            decidedColor = green;
        }

        return decidedColor;
    }

    /**
     * Gets the Ingredient's name.
     *
     * @return Ingredient's name.
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Gets the Ingredient's Expiry date.
     *
     * @return Ingredient's Expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Check if Ingredient nearing expiry by 7 DAYS.
     *
     * @return true if item is nearing expiry.
     */
    public boolean isNearExpiry() {
        LocalDate today = LocalDate.now();
        long daysRemaining = today.until(expiryDate, ChronoUnit.DAYS);
        return (daysRemaining < 7);
    }

    /**
     * Check if Ingredient has already expired.
     *
     * @return true if item has expired.
     */
    public boolean isPastExpiry() {
        LocalDate today = LocalDate.now();
        return today.isAfter(expiryDate);
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
