package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class AddCommand extends Command {
    /**
     * Constructor for AddCommand.
     */
    public AddCommand() {
    }

    /**
     * Executes the "add" command.
     *
     * @param ui           The ui object to interact with user.
     * @param parser       The parser object to parse user inputs.
     * @param itemList     The itemList object.
     * @param shoppingList The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    @Override
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList)
            throws FridgetException {

        // If the user input contains ;, the user wants to add multiple items at once
        if (ui.getCurrentUserInput().contains(";")) {
            ArrayList<Item> newItems = parser.parseMultipleItemsForAdding(ui.getCurrentUserInput());
            for (Item newItem : newItems) {
                addItemToItemList(ui, itemList, newItem, shoppingList);
            }
        } else {
            // Otherwise, add only one item
            Item newItem = parser.parseItemForAdding(ui.getCurrentUserInput());
            addItemToItemList(ui, itemList, newItem, shoppingList);
        }
    }

    private void addItemToItemList(Ui ui, ItemList itemList, Item newItem,
                                   ShoppingList shoppingList) throws FridgetException {
        String itemName = newItem.getItemName();
        int qty = itemList.addItem(newItem);
        if (qty == Integer.MAX_VALUE) {
            throw new FridgetException("You have reached the maximum possible amount of " + itemName
                    + "\nMax: 2147483647");
        } else if (qty > 1) {
            ui.printReactionToAddingExistingItem(newItem, qty);
        } else {
            ui.printReactionToAddingItem(newItem);
        }
        shoppingList.removeItem(newItem, newItem.getQuantity());
    }
}

