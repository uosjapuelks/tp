package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.notification.Notification;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

public class NotificationCommand extends Command {
    /**
     * Constructor for NotificationCommand.
     */
    public NotificationCommand() {
    }

    /**
     * Executes notification command.
     *
     * @param ui The ui object to interact with user.
     * @param parser The parser object to parse user inputs.
     * @param ingredientList The ingredientList object.
     * @param shoppingList The shoppingList object.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList) {
        if (Notification.isNotificationOn()) {
            Notification.setNotificationStatus(false);
            ui.printLine("Turning notification off!");
        } else {
            Notification.setNotificationStatus(true);
            ui.printLine("Turning notification on!");
        }
    }
}
