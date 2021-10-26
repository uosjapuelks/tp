package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class ShopListCommand extends Command {
    /**
     * Constructor for ShopListCommand.
     */
    public ShopListCommand() {
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList)
            throws FridgetException {
        ArrayList<Ingredient> listOfIngredients = shoppingList.getShoppingList();
        if (listOfIngredients.isEmpty()) {
            String errorMessage = "You currently have nothing in your shopping list.\n"
                    + "Input \"help\" to get started!";
            throw new FridgetException(errorMessage);
        }
        ui.printListMessage(listOfIngredients, "", true);
    }
}
