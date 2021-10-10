package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.notification.Notification;
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
     * @throws FridgetException if user types an incorrect value when prompted.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList) throws FridgetException {
        String nameOfItemToBeRemoved = parser.parseSearchTerm(ui.getCurrentUserInput());
        ArrayList<Ingredient> matchingItems = ingredientList.findAllMatchingIngredients(nameOfItemToBeRemoved);
        handleRemovalOfItem(ui, ingredientList, nameOfItemToBeRemoved, matchingItems);
    }

    /**
     * Handles all cases when user wants to remove an item based on the name of item to be removed.
     * @param ui The Ui which will send output and collect input from the user.
     * @param ingredientList The ingredientList where items are stored.
     * @param nameOfItemToBeRemoved The name of the item to be removed.
     * @param matchingItems The list of items in ingredientList which match the nameOfItemToBeRemoved.
     * @throws FridgetException if the user types an invalid index to remove from ingredientsList.
     */
    private void handleRemovalOfItem(Ui ui, IngredientList ingredientList, String nameOfItemToBeRemoved,
                                     ArrayList<Ingredient> matchingItems) throws FridgetException {
        if (matchingItems.size() == 0) {
            ui.printLine("No such item exists.");
            return;
        }

        // Make it convenient if the user only returns 1 result, result is same as input, & only one of item in list
        if (matchingItems.size() == 1) {
            Ingredient itemToBeRemoved = matchingItems.get(0);
            if (itemToBeRemoved.getIngredientName().equals(nameOfItemToBeRemoved)) {
                handleRemovalOfMultipleQuantity(ui, ingredientList, itemToBeRemoved);
                return;
            }
        }

        Ingredient itemToBeRemoved = ui.getItemToBeRemoved(matchingItems);
        assert ingredientList.containsIngredient(itemToBeRemoved);
        handleRemovalOfMultipleQuantity(ui, ingredientList, itemToBeRemoved);
    }

    /**
     * Handles the quantity to remove when user wants to remove an item.
     *
     * @param ui The Ui which will send output and collect input from the user.
     * @param ingredientList The ingredientList where items are stored.
     * @param itemToBeRemoved The item to be removed.
     * @throws FridgetException if the user types an invalid quantity to remove from ingredientsList.
     */
    private void handleRemovalOfMultipleQuantity(Ui ui, IngredientList ingredientList, Ingredient itemToBeRemoved)
            throws FridgetException {
        int qty;
        qty = ui.getQuantityToBeRemoved(itemToBeRemoved);
        ingredientList.removeIngredient(itemToBeRemoved, qty);
        ui.printReactionToRemovingIngredient(itemToBeRemoved, qty);
    }
}
