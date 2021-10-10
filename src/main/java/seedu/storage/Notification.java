package seedu.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification {
    public static final String NOTIFICATION = " ___________________________________________________\n"
            + "|                                                |X||\n"
            + "|      ___            _         __        ______    |\n"
            + "|     / _ \\___ __ _  (_)__  ___/ /__ ____/ / / /    |\n"
            + "|    / , _/ -_)  ' \\/ / _ \\/ _  / -_) __/_/_/_/     |\n"
            + "|   /_/|_|\\__/_/_/_/_/_//_/\\_,_/\\__/_/ (_|_|_)      |\n"
            + "|   =============================================   |\n"
            + "|                                                   |\n"
            + "|    \"If you don't take time to take care of your   |\n"
            + "|    health now, you're gonna have to make time     |\n"
            + "|    for feeling sick and tired later.\"             |\n"
            + "|                                                   |\n"
            + "|            Always remember to eat more            |\n"
            + "|              Fruits and Vegetables!               |\n"
            + "|___________________________________________________|";
    private LocalDateTime dateAndTime;
    private boolean isNotificationOn;

    /**
     * Constructor for notification.
     *
     * @param dateAndTime the date and time the notification is printed.
     * @param isNotificationOn status of whether notification is on or off.
     */
    public Notification(LocalDateTime dateAndTime, boolean isNotificationOn) {
        this.dateAndTime = dateAndTime;
        this.isNotificationOn = isNotificationOn;
    }

    /**
     * Prints notification when it is more than 6 hours since last notification
     * and notification is set to on.
     */
    public void printNotification() {
        if (isNotificationOn() && isMoreThan6Hours()) {
            System.out.println(NOTIFICATION + "\n__________________________________________");
            setDateAndTime(LocalDateTime.now());
        }
    }

    /**
     * Return true is notification is printed more than 6hours ago.
     *
     * @return Return true if time difference is more than 6hours.
     */
    public boolean isMoreThan6Hours() {
        return dateAndTime.compareTo(LocalDateTime.now().minusHours(6)) <= 0;
    }

    /**
     * Indicate whether notification is on or off.
     *
     * @return Return true if notification is on.
     */
    public boolean isNotificationOn() {
        return isNotificationOn;
    }

    /**
     * Sets the date and time the notification is printed.
     *
     * @param dateAndTime date and time since notification is printed.
     */
    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    /**
     * Set to toggle notification on or off.
     *
     * @param notificationOn A boolean value passed to turn on or off notification.
     */
    public void setNotificationStatus(boolean notificationOn) {
        this.isNotificationOn = notificationOn;
    }

    /**
     * Converted string format for saving into log text file.
     *
     * @return String containing date, time and status.
     */
    public String toString() {
        return dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " | " + (isNotificationOn ? "yes" : "no");
    }
}
