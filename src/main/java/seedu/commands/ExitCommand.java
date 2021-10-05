package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Constructor for CloseCommand.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes the CloseCommand.
     *
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList) {
        ui.printExitMessage();
    }
}
