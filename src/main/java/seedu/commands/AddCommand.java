package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.ui.Ui;

public class AddCommand extends Command {
    /**
     * Constructor for AddCommand.
     */
    public AddCommand() {
    }

    /**
     * Executes the "add" command.
     * @throws FridgetException exception thrown when Command class is executed.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList) {
        Ingredient newIngredient = parser.parseIngredientForAdding(ui.getCurrentUserInput());
        ingredientList.addIngredient(newIngredient);
        ui.printReactionToAddingIngredient(newIngredient);
    }



}
