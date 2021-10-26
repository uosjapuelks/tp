package seedu.data.ingredient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class Ingredient {
    protected String ingredientName;
    protected LocalDate expiryDate;
    protected int quantity;

    /**
     * Constructor for Ingredient.
     *
     * @param ingredientName name of the ingredient.
     * @param expiryDate     date of expiry for ingredient.
     */
    public Ingredient(String ingredientName, LocalDate expiryDate) {
        this.ingredientName = ingredientName;
        this.expiryDate = expiryDate;
        this.quantity = 1;
    }

    /**
     * Overloads constructor to start quantity from more than 1.
     *
     * @param ingredientName name of the ingredient.
     * @param expiryDate date of expiry for ingredient.
     * @param quantity quantity to start from.
     */
    public Ingredient(String ingredientName, LocalDate expiryDate, int quantity) {
        this.ingredientName = ingredientName;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    /**
     * Overloads constructor to ignore expiry date and start quantity from more than 1.
     *
     * @param ingredientName name of the ingredient.
     * @param quantity quantity to start from.
     */
    public Ingredient(String ingredientName, int quantity) {
        this.ingredientName = ingredientName;
        this.expiryDate = null;
        this.quantity = quantity;
    }

    /**
     * Adds a specified integer value to quantity of item.
     *
     * @param qty Amount of items to be added.
     */
    public void addQuantity(int qty) {
        quantity += qty;
    }

    /**
     * Subtracts a specified integer value to quantity of item.
     *
     * @param qty Amount of items to be subtracted.
     */
    public void removeQuantity(int qty) {
        quantity -= qty;
    }

    /**
     * Gets the Ingredient's quantity.
     *
     * @return Ingredient's quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int toQuantity) {
        this.quantity = toQuantity;
    }

    /**
     * String is represented as name + quantity + Expiry date that is colored depending on expiry status.
     *
     * @return Ingredient name, quantity and Expiry that is colored.
     */
    @Override
    public String toString() {
        String coloredExpiry = getColoredExpiryDate();
        return ingredientName + " | Qty: " + quantity + " | " + coloredExpiry;
    }

    /**
     * String is represented as name + quantity
     *
     * @return Ingredient name, quantity
     */
    public String toShopFormat() {
        return ingredientName + " | Qty: " + quantity;
    }

    /**
     * String used when adding items already existing in the list.
     *
     * @param qty Total quantity of item in the list.
     * @return Ingredient Name, change in quantity and Expiry that is colored.
     */
    public String addExistingToString(int qty) {
        assert qty > 1 : "Unable to perform this operation as quantity is less than or equal to 1";
        return ingredientName + " | Qty: " + (qty - 1) + "->" + qty + " | " + getColoredExpiryDate();
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
     * Gets the coloured expiry date.
     *
     * @return String containing coloured expiry date.
     */
    public String getColoredExpiryDate() {
        String expiry = expiryDate.format((DateTimeFormatter.ofPattern("dd MMM yyyy")));
        // TODO: implement in Milestone 2.0
        // String decidedColor = decideColor();
        return expiry;
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

    public String saveFormat() {
        return ingredientName + " | Qty: " + quantity + " | " + expiryDate;
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
