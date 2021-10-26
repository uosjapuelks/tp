package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.ui.Ui;

public class HelpCommand extends Command {
    private static final String COMMAND_LIST = "List of commands available:\n\n"
            + "add INGREDIENT_NAME /EXPIRY_DATE(format: yyyy-mm-dd) [eg. add bacon /2022-11-11]\n"
            + "-> Adds an ingredient and its expiry date to the ingredient list.\n\n"
            + "find KEYWORD [eg. find bacon]\n"
            + "-> Find and print all ingredient associated with keyword.\n\n"
            + "remove INGREDIENT_NAME [eg. delete bacon]\n"
            + "-> Removes the ingredient from the ingredient list.\n\n"
            + "list -e\n"
            + "-> List out all the ingredients starting with earliest expiry date.\n\n"
            + "list -r\n"
            + "-> List out all the ingredients based on the order added.\n\n"
            + "list\n"
            + "-> List out all the ingredients in alphabetical order.\n\n"
            + "expiring\n"
            + "-> Prints out ingredients that have expired or are expiring within a week.\n\n"
            + "update INGREDIENT_NAME [eg. update egg]\n"
            + "-> Prompts a quantity change for the specified item name.\n\n"
            + "notifs\n"
            + "-> Toggle the notification on or off depending on previous state. Default mode is on.\n\n"
            + "reset\n"
            + "-> Deletes all the previous ingredient entries from the reader.\n\n"
            + "exit\n"
            + "-> Exits the program.\n\n"
            + "For more information about each command please visit our User Guide.\n"
            + "Link: https://ay2122s1-cs2113t-w12-4.github.io/tp/UserGuide.html";

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand() {
    }

    /**
     * Executes the "help" command.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList) {
        ui.printLine(COMMAND_LIST);
    }
}
