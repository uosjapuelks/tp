package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class UpdateCommand extends Command {
    /**
     * Constructor for QuantityCommand.
     */
    public UpdateCommand() {
    }

    /**
     * Executes the command.
     *
     * @throws FridgetException if user types an incorrect value when prompted.
     */
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList)
            throws FridgetException {
        String targetItem = parser.parseSearchTerm(ui.getCurrentUserInput(), Parser.CommandType.UPDATE);
        if (targetItem.contains(" | ") | targetItem.contains("/")) {
            throw new FridgetException("You are not able to use '/' and ' | ' in ingredient name.");
        }
        ArrayList<Ingredient> matchingItems = ingredientList.findAllMatchingIngredients(targetItem);

        ui.printIfNotFoundMessage(matchingItems);

        if (matchingItems.size() > 0) {
            boolean correctTargetIngredient = true;
            if (matchingItems.size() == 1 && !matchingItems.get(0).getIngredientName().equals(targetItem)) {
                correctTargetIngredient = ui.giveSuggestion(matchingItems.get(0));
            }
            if (correctTargetIngredient) {
                Ingredient itemToUpdate = ui.matchItem(matchingItems, Ui.CommandType.UPDATE);
                int newQty = ui.getUpdate(itemToUpdate);
                int qtyDiff = newQty - itemToUpdate.getQuantity();
                ingredientList.updateQuantity(itemToUpdate, newQty);
                updateShopList(shoppingList, itemToUpdate, qtyDiff);
                ui.acknowledgeUpdate(itemToUpdate);
            }
        }
    }

    private void updateShopList(ShoppingList shoppingList, Ingredient updatedIngredient, int qty) {
        shoppingList.removeIngredient(updatedIngredient, qty);
    }
}
