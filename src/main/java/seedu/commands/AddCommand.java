package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
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
     * @param ui             The ui object to interact with user.
     * @param parser         The parser object to parse user inputs.
     * @param ingredientList The ingredientList object.
     * @param shoppingList   The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList)
            throws FridgetException {
        if (ui.getCurrentUserInput().contains(";")) {
            ArrayList<Ingredient> newIngredients = parser.parseMultipleIngredientsForAdding(ui.getCurrentUserInput());
            for (Ingredient newIngredient : newIngredients) {
                addIngredientToIngredientList(ui, ingredientList, newIngredient, shoppingList);
            }
        } else {
            Ingredient newIngredient = parser.parseIngredientForAdding(ui.getCurrentUserInput());
            addIngredientToIngredientList(ui, ingredientList, newIngredient, shoppingList);
        }
    }

    private void addIngredientToIngredientList(Ui ui, IngredientList ingredientList, Ingredient newIngredient,
                                               ShoppingList shoppingList) throws FridgetException {
        String itemName = newIngredient.getIngredientName();
        if (itemName.contains("|")) {
            throw new FridgetException("Please do not use '|' in your ingredient name.");
        }
        int qty = ingredientList.addIngredient(newIngredient);
        if (qty == Integer.MAX_VALUE) {
            throw new FridgetException("You have reached the maximum possible amount of " + itemName
                    + "\nMax: 2147483647");
        } else if (qty > 1) {
            ui.printReactionToAddingExistingIngredient(newIngredient, qty);
        } else {
            ui.printReactionToAddingIngredient(newIngredient);
        }
        shoppingList.removeIngredient(newIngredient, newIngredient.getQuantity());
    }
}

