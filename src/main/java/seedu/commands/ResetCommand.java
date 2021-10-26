package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

public class ResetCommand extends Command {
    /**
     * Constructor for ResetCommand.
     */
    public ResetCommand() {
    }

    /**
     * Executes the "reset" command.
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
        if (ingredientList.getIngredientList("r").isEmpty()) {
            throw new FridgetException("You currently have nothing in your fridge.");
        }
        if (ui.getResetReconfirm(Ui.CommandType.RESET)) {
            ingredientList.resetList();
            ui.printResetMessage();
        }
    }
}
