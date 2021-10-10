package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.Notification;
import seedu.ui.Ui;

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
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, Notification notification) throws FridgetException {
        Ingredient newIngredient = parser.parseIngredientForAdding(ui.getCurrentUserInput());
        ingredientList.addIngredient(newIngredient);
        ui.printReactionToAddingIngredient(newIngredient);
    }
}
