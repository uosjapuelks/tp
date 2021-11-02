package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.parser.Parser;
import seedu.storage.ItemList;
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
     * @param ui The ui object to interact with user.
     * @param parser The parser object to parse user inputs.
     * @param itemList The itemList object.
     * @param shoppingList The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList)
            throws FridgetException {
        String targetItem = parser.parseSearchTerm(ui.getCurrentUserInput(), Parser.CommandType.UPDATE);
        if (targetItem.contains(" | ") | targetItem.contains("/")) {
            throw new FridgetException("You are not able to use '/' and ' | ' in item name.");
        }
        ArrayList<Item> matchingItems = itemList.findAllMatchingItems(targetItem);

        ui.printIfNotFoundMessage(matchingItems);

        if (matchingItems.size() > 0) {
            boolean correctTargetItem = true;
            if (matchingItems.size() == 1 && !matchingItems.get(0).getItemName().equals(targetItem)) {
                correctTargetItem = ui.giveSuggestion(matchingItems.get(0));
            }
            if (correctTargetItem) {
                Item itemToUpdate = ui.matchItem(matchingItems, Ui.CommandType.UPDATE);
                int newQty = ui.getUpdate(itemToUpdate);
                int qtyDiff = newQty - itemToUpdate.getQuantity();
                itemList.updateQuantity(itemToUpdate, newQty);
                updateShopList(shoppingList, itemToUpdate, qtyDiff);
                ui.acknowledgeUpdate(itemToUpdate);
            }
        }
    }

    /**
     * Updates the shopping list.
     *
     * @param shoppingList The shoppingList object.
     * @param updatedItem The item updated.
     * @param qty The difference in quantity of the update.
     */
    private void updateShopList(ShoppingList shoppingList, Item updatedItem, int qty) {
        if (qty <= 0) {
            return;
        }
        shoppingList.removeItem(updatedItem, qty);
    }
}
