package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.notification.Notification;
import seedu.ui.Ui;

public class HelpCommand extends Command {
    private static final String COMMAND_LIST = "Generating command list...\n"
            + "__________________________________________\n"
            + "List of commands available:\n\n"
            + "add <INGREDIENT_NAME> </EXPIRY_DATE(format: yyyy-mm-dd)> [eg. add bacon /2022-11-11]\n"
            + "-> Adds an ingredient and its expiry date to the ingredient list.\n\n"
            + "find <KEYWORD> [eg. find bacon]\n"
            + "-> Find and print all ingredient associated with keyword.\n\n"
            + "remove <INGREDIENT_NAME> [eg. delete bacon]\n"
            + "-> Removes the ingredient from the ingredient list.\n\n"
            + "list -e\n"
            + "-> List out all the ingredients starting with earliest expiry date.\n\n"
            + "list -r\n"
            + "-> List out all the ingredients based on the order added.\n\n"
            + "list\n"
            + "-> List out all the ingredients in alphabetical order.\n\n"
            + "expiring\n"
            + "-> Prints out ingredients expiring within a week\n\n"
            + "notifs\n"
            + "-> Toggle the notification on or off depending on previous state. Default mode is on.\n\n"
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
