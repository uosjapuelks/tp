package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class RemoveCommand extends Command {
    /**
     * Constructor for Remove Command.
     */
    public RemoveCommand() {
    }

    /**
     * Executes the command.
     *
     * @throws FridgetException if user types an incorrect value when prompted.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList)
            throws FridgetException {
        String nameOfItemToBeRemoved = parser.parseSearchTerm(ui.getCurrentUserInput(), Parser.CommandType.REMOVE);
        if (nameOfItemToBeRemoved.contains(" | ") | nameOfItemToBeRemoved.contains("/")) {
            throw new FridgetException("You are not able to use '/' and ' | ' in ingredient names.");
        }
        ArrayList<Ingredient> matchingItems = ingredientList.findAllMatchingIngredients(nameOfItemToBeRemoved);
        handleRemovalOfItem(ui, ingredientList, shoppingList, nameOfItemToBeRemoved, matchingItems);
    }

    /**
     * Handles all cases when user wants to remove an item based on the name of item to be removed.
     *
     * @param ui The Ui which will send output and collect input from the user.
     * @param ingredientList The ingredientList where items are stored.
     * @param nameOfItemToBeRemoved The name of the item to be removed.
     * @param matchingItems The list of items in ingredientList which match the nameOfItemToBeRemoved.
     * @throws FridgetException if the user types an invalid index to remove from ingredientsList.
     */
    private void handleRemovalOfItem(Ui ui, IngredientList ingredientList, ShoppingList shoppingList
            , String nameOfItemToBeRemoved, ArrayList<Ingredient> matchingItems) throws FridgetException {
        // If there are no matching items, let the user know
        ui.printIfNotFoundMessage(matchingItems);

        // Remove item automatically if matching item is unique
        if (matchingItems.size() == 1) {
            boolean acceptDefault = true;
            Ingredient itemToBeRemoved = matchingItems.get(0);
            if (!itemToBeRemoved.getIngredientName().equals(nameOfItemToBeRemoved)) {
                acceptDefault = ui.giveSuggestion(itemToBeRemoved);
            }
            if (acceptDefault) {
                handleRemovalOfMultipleQuantity(ui, ingredientList, shoppingList, itemToBeRemoved);
                return;
            }
        }

        if (matchingItems.size() > 1) {
            Ingredient itemToBeRemoved = ui.matchItem(matchingItems, Ui.CommandType.REMOVE);
            assert ingredientList.containsIngredient(itemToBeRemoved);
            handleRemovalOfMultipleQuantity(ui, ingredientList, shoppingList, itemToBeRemoved);
        }
    }

    /**
     * Handles the quantity to remove when user wants to remove an item.
     *
     * @param ui The Ui which will send output and collect input from the user.
     * @param ingredientList The ingredientList where items are stored.
     * @param itemToBeRemoved The item to be removed.
     * @throws FridgetException if the user types an invalid quantity to remove from ingredientsList.
     */
    private void handleRemovalOfMultipleQuantity(Ui ui, IngredientList ingredientList, ShoppingList shoppingList
            , Ingredient itemToBeRemoved) throws FridgetException {
        int qty;
        qty = ui.getQuantityToBeRemoved(itemToBeRemoved);
        boolean isRemoved = ingredientList.removeIngredient(itemToBeRemoved, qty);
        ui.printReactionToRemovingIngredient(itemToBeRemoved, qty);

        if (isRemoved) {
            updateShopList(ui,shoppingList, itemToBeRemoved);
        }
    }

    private void updateShopList(Ui ui, ShoppingList shoppingList, Ingredient itemRemoved)
            throws FridgetException {
        try {
            int qty = ui.getShopUpdateQuantity(itemRemoved);
            if (qty > 0) {
                Ingredient addedIngredient = new Ingredient(itemRemoved.getIngredientName(), qty);
                shoppingList.addIngredient(addedIngredient, qty);
                ui.printShopUpdateMessage(addedIngredient);
            }
        } catch (NumberFormatException e) {
            throw new FridgetException("Quantity input is invalid");
        }
    }
}
