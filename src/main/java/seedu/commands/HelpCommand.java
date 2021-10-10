package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.Notification;
import seedu.ui.Ui;

public class HelpCommand extends Command {
    private static final String COMMAND_LIST = "Generating command list...\n"
            + "__________________________________________\n"
            + "List of commands available:\n\n"
            + "add <name of ingredient> </expiryDate(format: yyyy-mm-dd)> [eg. add bacon /2022-11-11]\n"
            + "-> Adds an ingredient and its expiry date to the ingredient list.\n\n"
            + "remove <name of ingredient> [eg. delete bacon]\n"
            + "-> Removes the ingredient from the ingredient list.\n\n"
            + "list\n"
            + "-> List out all the ingredients in the ingredient list.\n\n"
            + "reset\n"
            + "-> Deletes all the previous ingredient entries from the reader.\n\n"
            + "exit\n"
            + "-> Exits the program.";

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand() {
    }

    /**
     * Executes the "help" command.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, Notification notification) {
        ui.printLine(COMMAND_LIST);
    }
}
