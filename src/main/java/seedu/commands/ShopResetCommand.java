package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

public class ShopResetCommand extends Command {
    /**
     * Constructor for ShopResetCommand.
     */
    public ShopResetCommand() {
    }

    /**
     * Executes the "shopreset" command.
     *
     * @param ui The ui object to interact with user.
     * @param parser The parser object to parse user inputs.
     * @param ingredientList The ingredientList object.
     * @param shoppingList The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList)
            throws FridgetException {
        if (shoppingList.getShoppingList().isEmpty()) {
            throw new FridgetException("You currently have nothing in your shopping list.");
        }

        if (ui.getResetReconfirm()) {
            shoppingList.resetList();
            ui.printShopResetMessage();
        }
    }
}
