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
    public void execute(Ui ui, Parser parser, IngredientList ingredientList) {
        if (Notification.isNotificationOn()) {
            Notification.setNotificationStatus(false);
            ui.printLine("Turning notification off!");
        } else {
            Notification.setNotificationStatus(true);
            ui.printLine("Turning notification on!");
        }
    }
}
