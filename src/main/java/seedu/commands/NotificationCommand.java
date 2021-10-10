package seedu.commands;

import seedu.storage.Notification;

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
    public void execute(Notification notification) {
        notification.setNotificationStatus(!notification.isNotificationOn());
    }
}
