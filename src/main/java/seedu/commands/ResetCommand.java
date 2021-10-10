package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.Notification;
import seedu.ui.Ui;

public class ResetCommand extends Command {
    /**
     * Constructor for ResetCommand.
     */
    public ResetCommand() {
    }

    /**
     * Executes the "reset" command.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, Notification notification) {
        if (ui.getResetReconfirm()) {
            ingredientList.resetList();
            ui.printResetMessage();
        }
    }
}
