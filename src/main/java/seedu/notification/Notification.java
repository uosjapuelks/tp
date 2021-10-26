package seedu.notification;

import seedu.data.ingredient.Ingredient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Notification {
    private static final String HEALTH_NOTIFICATION = " ___________________________________________________\n"
            + "|                      HEALTH!                      |\n"
            + "|===================================================|\n"
            + "|      ___            _         __        ______    |\n"
            + "|     / _ \\___ __ _  (_)__  ___/ /__ ____/ / / /    |\n"
            + "|    / , _/ -_)  ' \\/ / _ \\/ _  / -_) __/_/_/_/     |\n"
            + "|   /_/|_|\\__/_/_/_/_/_//_/\\_,_/\\__/_/ (_|_|_)      |\n"
            + "|   ---------------------------------------------   |\n"
            + "|                                                   |\n"
            + "|    \"If you don't take time to take care of your   |\n"
            + "|    health now, you're gonna have to make time     |\n"
            + "|    for feeling sick and tired later.\"             |\n"
            + "|                                                   |\n"
            + "|            Always remember to eat more            |\n"
            + "|              Fruits and Vegetables!               |\n"
            + "|___________________________________________________|";
    private static final String EXPIRING_NOTIFICATION = " ___________________________________________________\n"
            + "|                      EXPIRY!                      |\n"
            + "|===================================================|\n"
            + "|      ___            _         __        ______    |\n"
            + "|     / _ \\___ __ _  (_)__  ___/ /__ ____/ / / /    |\n"
            + "|    / , _/ -_)  ' \\/ / _ \\/ _  / -_) __/_/_/_/     |\n"
            + "|   /_/|_|\\__/_/_/_/_/_//_/\\_,_/\\__/_/ (_|_|_)      |\n"
            + "|___________________________________________________|\n";
    private static final String FIVE_SPACE_INDENTATION = "     ";
    private static final String SEPARATOR_LINE = "__________________________________________";
    private static final String NOTIFICATION_SEPARATOR = " --------------------------------------------------- \n";
    private static final String NOTIFICATION_CLOSING_LINE = "|___________________________________________________|\n";
    private static final String EXPIRED_MESSAGE = " >> Items expired! Please discard ASAP:";
    private static final String EXPIRING_MESSAGE = " >> Please use them before they expire:";
    private LocalDateTime dateAndTime = LocalDateTime.now().minusHours(4);
    private static boolean isNotificationOn = true;

    /**
     * Constructor for notification.
     */
    public Notification() {
    }

    public void printMessage(String content) {
        System.out.println(content);
    }

    /**
     * Prints out all notifications.
     *
     * @param listOfIngredients list of ingredients stored in ingredientList.
     */
    public void printNotification(ArrayList<Ingredient> listOfIngredients) {
        if (isNotificationOn() && isMoreThan4Hours()) {
            printHealthNotification();
            printExpiringNotification(listOfIngredients);
        }
    }

    /**
     * Prints health notification when it is more than 4 hours since last notification
     * and notification is set to on.
     */
    public void printHealthNotification() {
        printMessage(HEALTH_NOTIFICATION + "\n" + SEPARATOR_LINE);
        setDateAndTime(LocalDateTime.now());
    }

    /**
     * Print out expiring notification for items expiring and/or expired if any.
     *
     * @param listOfIngredients list of ingredients stored in ingredientList.
     */
    public void printExpiringNotification(ArrayList<Ingredient> listOfIngredients) {
        listOfIngredients.sort(Ingredient.IngExpiryComparator);
        ArrayList<Ingredient> expiringList = new ArrayList<Ingredient>();
        for (Ingredient ingredient : listOfIngredients) {
            if (ingredient.isNearExpiry()) {
                expiringList.add(ingredient);
            }
        }
        if (expiringList.size() > 0) {
            int i = 0;
            i = printExpiredItemsAndGetLastIndex(expiringList, i);
            printExpiringItems(expiringList, i);
        }
    }

    /**
     * Print out items that are expiring.
     *
     * @param expiringList an ArrayList of items that are expiring within a week or expired.
     * @param i variable to traverse the list.
     */
    private void printExpiringItems(ArrayList<Ingredient> expiringList, int i) {
        if (expiringList.size() - i > 0) {
            int index = 1;
            printMessage( NOTIFICATION_SEPARATOR + EXPIRING_MESSAGE);
            for (; i < expiringList.size(); i++) {
                String beforeIngredient = FIVE_SPACE_INDENTATION + index + ". ";
                printMessage(beforeIngredient + expiringList.get(i));
                index++;
            }
            printMessage(NOTIFICATION_CLOSING_LINE + SEPARATOR_LINE);
        }
    }

    /**
     * Print out items that are expired from a list of expiring items and return the last index it traverse.
     *
     * @param expiringList an ArrayList of items that are expiring within a week or expired.
     * @param i variable to traverse the list.
     * @return int value of i of the last item traversed.
     */
    private int printExpiredItemsAndGetLastIndex(ArrayList<Ingredient> expiringList, int i) {
        if (expiringList.get(i).isExpired()) {
            printMessage(EXPIRING_NOTIFICATION + EXPIRED_MESSAGE);
            int index = 1;
            for (; expiringList.get(i).isExpired(); i++) {
                String beforeIngredient = FIVE_SPACE_INDENTATION + index + ". ";
                printMessage(beforeIngredient + expiringList.get(i));
                index++;
            }
        }
        return i;
    }

    /**
     * Return true if notification is printed more than 4 hours ago.
     *
     * @return Return true if time difference is more than 4 hours.
     */
    public boolean isMoreThan4Hours() {
        return dateAndTime.compareTo(LocalDateTime.now().minusHours(4)) <= 0;
    }

    /**
     * Indicate whether notification is on or off.
     *
     * @return Return true if notification is on.
     */
    public static boolean isNotificationOn() {
        return isNotificationOn;
    }

    /**
     * Sets the date and time when the notification is printed.
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
    public static void setNotificationStatus(boolean notificationOn) {
        isNotificationOn = notificationOn;
    }

    /**
     * Converted string format for saving into log text file.
     *
     * @return String containing date, time and status.
     */
    public String toString() {
        String dateTimeAndStatus =  dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " | " + (isNotificationOn ? "yes" : "no");
        assert dateTimeAndStatus.contains(" | ");
        return dateTimeAndStatus;
    }
}
