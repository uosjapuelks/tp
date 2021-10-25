package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class QuantityCommand extends Command {
    /**
     * Constructor for QuantityCommand
     */
    public QuantityCommand() {
    }

    public void execute(Ui ui, Parser parser, IngredientList ingredientList) throws FridgetException {
        String targetItem = parser.parseSearchTerm(ui.getCurrentUserInput(), Parser.CommandType.QUANTITY);
        if (targetItem.contains(" | ") | targetItem.contains("/")) {
            throw new FridgetException("You are not able to use '/' and '|' in ingredient name.");
        }
        ArrayList<Ingredient> matchingItems = ingredientList.findAllMatchingIngredients(targetItem);
        ui.printIfNotFoundMessage(matchingItems);
        Ingredient itemToUpdate = ui.getCorrectItem(matchingItems);
    }
}
