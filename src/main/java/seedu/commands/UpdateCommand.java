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
     * @param ui           The ui object to interact with user.
     * @param parser       The parser object to parse user inputs.
     * @param itemList     The itemList object.
     * @param shoppingList The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList)
            throws FridgetException {
        String targetItem = parser.parseSearchTerm(ui.getCurrentUserInput(), Parser.CommandType.UPDATE);
        ArrayList<Item> matchingItems = itemList.findAllMatchingItems(targetItem);

        Item itemToUpdate = ui.matchItem(matchingItems, targetItem, Ui.CommandType.UPDATE);
        int newQty = ui.getUpdate(itemToUpdate);
        int qtyDiff = newQty - itemToUpdate.getQuantity();
        itemList.updateQuantity(itemToUpdate, newQty);
        updateShopList(shoppingList, itemToUpdate, qtyDiff);
        ui.acknowledgeUpdate(itemToUpdate);
    }

    /**
     * Updates the shopping list.
     *
     * @param shoppingList The shoppingList object.
     * @param updatedItem  The item updated.
     * @param qty          The difference in quantity of the update.
     */
    private void updateShopList(ShoppingList shoppingList, Item updatedItem, int qty) {
        if (qty <= 0) {
            return;
        }
        shoppingList.removeItem(updatedItem, qty);
    }
}
