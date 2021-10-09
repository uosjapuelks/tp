package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.ui.Ui;

import java.util.ArrayList;

public class RemoveCommand extends Command {
    public RemoveCommand() {
    }

    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList) throws FridgetException {
        String nameOfItemToBeRemoved = parser.parseSearchTerm(ui.getCurrentUserInput());
        ArrayList<Ingredient> matchingItems = ingredientList.findAllMatchingIngredients(nameOfItemToBeRemoved);
        handleRemovalOfItem(ui, ingredientList, nameOfItemToBeRemoved, matchingItems);
    }

    private void handleRemovalOfItem(Ui ui, IngredientList ingredientList, String nameOfItemToBeRemoved,
                                     ArrayList<Ingredient> matchingItems) throws FridgetException {
        if (matchingItems.size() == 0) {
            ui.printLine("No such item exists.");
            return;
        }

        // Make it convenient if the user only returns 1 result, result is same as input, & only one of item in list
        if (matchingItems.size() == 1) {
            Ingredient itemToBeRemoved = matchingItems.get(0);
            if (itemToBeRemoved.getIngredientName().equals(nameOfItemToBeRemoved)) {
                ingredientList.removeIngredient(itemToBeRemoved);
                ui.printReactionToRemovingIngredient(itemToBeRemoved);
                return;
            }
        }

        int indexOfItemToBeRemoved = ui.getIndexOfItemToBeRemoved(matchingItems) - 1;
        Ingredient itemToBeRemoved = matchingItems.get(indexOfItemToBeRemoved);
        ingredientList.removeIngredient(itemToBeRemoved);
        ui.printReactionToRemovingIngredient(itemToBeRemoved);
    }
}
