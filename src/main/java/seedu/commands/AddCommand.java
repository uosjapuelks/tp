package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

public class AddCommand extends Command {
    /**
     * Constructor for AddCommand.
     */
    public AddCommand() {
    }

    /**
     * Executes the "add" command.
     *
     * @param ui             The ui object to interact with user.
     * @param parser         The parser object to parse user inputs.
     * @param ingredientList The ingredientList object.
     * @param shoppingList   The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList)
            throws FridgetException {
        Ingredient newIngredient = parser.parseIngredientForAdding(ui.getCurrentUserInput());
        if (newIngredient.getIngredientName().contains(" | ")) {
            throw new FridgetException("Please do not use ' | ' in your ingredient name.");
        }
        int qty = ingredientList.addIngredient(newIngredient);
        if (qty > 1) {
            ui.printReactionToAddingExistingIngredient(newIngredient, qty);
        } else {
            ui.printReactionToAddingIngredient(newIngredient);
        }
        shoppingList.removeIngredient(newIngredient, newIngredient.getQuantity());
    }
}

