package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.ui.Ui;

public abstract class Command {

    /**
     * Constructor for Command.
     */
    public Command() {
    }

    /**
     * Executes the command.
     * @throws FridgetException exception thrown when Command class is executed.
     */
    public abstract void execute(Ui ui, Parser parser, IngredientList ingredientList);
}
