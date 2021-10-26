package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.notification.Notification;
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
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList) throws FridgetException {
        if (ui.getCurrentUserInput().contains(";")) {
            ArrayList<Ingredient> newIngredients = parser.parseMultipleIngredientsForAdding(ui.getCurrentUserInput());
            for (Ingredient newIngredient : newIngredients) {
                addIngredientToIngredientList(ui, ingredientList, newIngredient);
            }
        } else {
            Ingredient newIngredient = parser.parseIngredientForAdding(ui.getCurrentUserInput());
            addIngredientToIngredientList(ui, ingredientList, newIngredient);
        }


    }

    private void addIngredientToIngredientList(Ui ui, IngredientList ingredientList, Ingredient newIngredient) throws FridgetException {
        if (newIngredient.getIngredientName().contains(" | ")) {
            throw new FridgetException("Please do not use ' | ' in your ingredient name.");
        }
        int qty = ingredientList.addIngredient(newIngredient);
        if (qty > 1) {
            ui.printReactionToAddingExistingIngredient(newIngredient, qty);
        } else {
            ui.printReactionToAddingIngredient(newIngredient);
        }
    }
}
