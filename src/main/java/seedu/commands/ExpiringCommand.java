package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.notification.Notification;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class ExpiringCommand extends Command {
    /**
     * Constructor for ExpiringCommand.
     */
    public ExpiringCommand() {
    }

    /**
     * Executes the "expiring" command.
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
        String sortByExpiry = "e";
        ArrayList<Ingredient> listOfIngredients = ingredientList.getIngredientList(sortByExpiry);
        ui.printExpiringMessage(listOfIngredients);
    }
}
