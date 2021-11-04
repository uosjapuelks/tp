package seedu.storage;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.notification.Notification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class Storage {
    private final ItemList itemList;
    private final ShoppingList shoppingList;
    private final Notification notification;
    private final String fileDirectory;
    private final String listFilePath;
    private final String logFilePath;
    private final String shopFilePath;

    private static final Logger logger = Logger.getLogger("logger");
    private static final String REGEX_DATA_SEPARATOR = " \\| ";

    /**
     * A constructor to save data into text file.
     *
     * @param itemList     The ItemList object.
     * @param shoppingList The ShoppingList object.
     * @param notification The notification object.
     * @param listFilePath Pathway of item list file storage.
     * @param logFilePath  Pathway of user log file storage.
     * @param shopFilePath Pathway of shopping list file storage.
     */
    public Storage(ItemList itemList, ShoppingList shoppingList, Notification notification,
                   String listFilePath, String logFilePath, String shopFilePath) {
        String[] fileComponents = listFilePath.split("/");
        this.fileDirectory = fileComponents[0];
        this.itemList = itemList;
        this.shoppingList = shoppingList;
        this.notification = notification;
        this.listFilePath = listFilePath;
        this.logFilePath = logFilePath;
        this.shopFilePath = shopFilePath;

        try {
            loadFile();
        } catch (IOException | FridgetException e) {
            logger.log(Level.WARNING, "in storage, unable to load existing file");
        }
    }

    /**
     * Initialises text files if not present.
     * Loads all the data from the item list text file.
     *
     * @throws IOException The error thrown from file IO operations.
     */
    protected void loadFile() throws IOException, FridgetException {
        File dataDirectory = new File(fileDirectory);
        File listFile = new File(listFilePath);
        File logFile = new File(logFilePath);
        File shopFile = new File(shopFilePath);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            listFile.createNewFile();
            logFile.createNewFile();
            shopFile.createNewFile();
            return;
        }

        if (!(listFile.exists() && logFile.exists() && shopFile.exists())) {
            if (!listFile.exists()) {
                listFile.createNewFile();
            }

            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            if (!shopFile.exists()) {
                shopFile.createNewFile();
            }
            return;
        }

        Scanner listScanner = new Scanner(listFile);
        if (listScanner == null) {
            logger.log(Level.WARNING, "Please restart the program! Data storage has been corrupted.");
        }
        while (listScanner.hasNext()) {
            String line = listScanner.nextLine();
            String[] listDataComponents = line.split(REGEX_DATA_SEPARATOR);
            addSavedItem(listDataComponents);
        }

        Scanner logScanner = new Scanner(logFile);
        if (logScanner == null) {
            logger.log(Level.WARNING, "Please restart the program! Data storage has been corrupted.");
        }
        if (logScanner.hasNext()) {
            addSavedNotification(logScanner.nextLine());
        }

        Scanner shopScanner = new Scanner(shopFile);
        if (shopScanner == null) {
            logger.log(Level.WARNING, "Please restart the program! Data storage has been corrupted.");
        }
        while (shopScanner.hasNext()) {
            String line = shopScanner.nextLine();
            String[] shopDataComponents = line.split(REGEX_DATA_SEPARATOR);
            addSavedShopItem(shopDataComponents);
        }
    }

    /**
     * Adds saved item into the item list.
     *
     * @param listDataComponents The details of the item.
     */
    protected void addSavedItem(String[] listDataComponents) throws FridgetException {
        int quantity = parseInt(listDataComponents[1].substring(4).trim());
        LocalDate expiry = LocalDate.parse(listDataComponents[2].trim());
        Item savedItem = new Item(listDataComponents[0], expiry, quantity);
        itemList.addItem(savedItem);
    }

    /**
     * Adds the log date and time, and notification on/off status.
     *
     * @param savedDateTimeAndStatus String containing date, time and status.
     */
    protected void addSavedNotification(String savedDateTimeAndStatus) {
        String[] splitString = savedDateTimeAndStatus.split(REGEX_DATA_SEPARATOR);
        notification.setDateAndTime(LocalDateTime.parse(splitString[0],
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        Notification.setNotificationStatus(!splitString[1].equals("no"));
    }

    /**
     * Adds the saved items in shopping list file into the shopping list.
     *
     * @param shopDataComponents The details of the item.
     */
    protected void addSavedShopItem(String[] shopDataComponents) {
        int quantity = parseInt(shopDataComponents[1].substring(4).trim());
        Item savedItem = new Item(shopDataComponents[0], quantity);
        shoppingList.addItem(savedItem, quantity);
    }

    /**
     * Updates the items list text file.
     *
     * @param items The current list of items.
     * @throws IOException The error thrown from file IO operations.
     */
    public void updateListFile(ArrayList<Item> items) throws IOException {
        FileWriter fileWriter = new FileWriter(listFilePath);
        for (Item item : items) {
            assert item != null : "Item must not be null!";
            fileWriter.write(item.saveFormat());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Updates the log text file.
     *
     * @param notification Notification object.
     * @throws IOException The error thrown from file IO operations.
     */
    public void updateLogFile(Notification notification) throws IOException {
        FileWriter fileWriter = new FileWriter(logFilePath);
        assert notification != null : "Notification must not be null!";
        fileWriter.write(notification.toString());
        fileWriter.close();
    }

    /**
     * Updated the shop list text file.
     *
     * @param items The current list of items in the shopping list.
     * @throws IOException The error thrown from file IO operations.
     */
    public void updateShopFile(ArrayList<Item> items) throws IOException {
        FileWriter fileWriter = new FileWriter(shopFilePath);
        for (Item item : items) {
            fileWriter.write(item.toShopFormat());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Updates all the text files.
     *
     * @param storedItems   The current list of items.
     * @param shoppingItems The current shopping list of items.
     * @param notification  Notification object.
     */
    public void updateFiles(ArrayList<Item> storedItems, ArrayList<Item> shoppingItems,
                            Notification notification) {
        try {
            updateListFile(storedItems);
            updateLogFile(notification);
            updateShopFile(shoppingItems);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error while trying to update item list file.");
        }
    }
}
