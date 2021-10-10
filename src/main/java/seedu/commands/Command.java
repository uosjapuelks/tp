package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.Notification;
import seedu.ui.Ui;

public class Command {
    protected boolean isExit = false;

    /**
     * Constructor for Command.
     */
    public Command() {
    }

    /**
     * Check status of program, if it should end.
     *
     * @return true if program should keep running and false when exit is called.
     */
    public boolean exitNotRequired() {
        return !isExit;
    }

    /**
     * Executes the command.
     */
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, Notification notification) throws FridgetException {
    }
}
