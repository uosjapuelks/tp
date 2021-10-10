package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.notification.Notification;
import seedu.ui.Ui;

public class NotificationCommand extends Command {
    /**
     * Constructor for NotificationCommand.
     */
    public NotificationCommand() {
    }

    /**
     * Executes notification command.
     */
    @Override
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, Notification notification) {
        if (notification.isNotificationOn()) {
            notification.setNotificationStatus(false);
            ui.printLine("Notification off!");
        } else {
            notification.setNotificationStatus(true);
            ui.printLine("Notification on!");
        }
    }
}
