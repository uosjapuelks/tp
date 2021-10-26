package seedu.storage;

import seedu.notification.Notification;
import seedu.data.ingredient.Ingredient;

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
    private final IngredientList ingredientList;
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
     * @param listFilePath pathway of ingredient list file storage.
     * @param logFilePath pathway of user log file storage.
     */
    public Storage(IngredientList ingredientList, ShoppingList shoppingList, Notification notification
            , String listFilePath, String logFilePath,  String shopFilePath) {
        String[] fileComponents = listFilePath.split("/");
        this.ingredientList = ingredientList;
        this.shoppingList = shoppingList;
        this.notification = notification;
        this.fileDirectory = fileComponents[0];
        this.listFilePath = listFilePath;
        this.logFilePath = logFilePath;
        this.shopFilePath = shopFilePath;

        try {
            loadFile();
        } catch (IOException e) {
            logger.log(Level.WARNING, "in storage, unable to load existing file");
        }
    }

    /**
     * Initialises text files if not present.
     * Loads all the data from the ingredient list text file.
     *
     * @throws IOException The error thrown from file IO operations.
     */
    protected void loadFile() throws IOException {
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
            addSavedIngredient(listDataComponents);
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
            addSavedShopIngredient(shopDataComponents);
        }
    }

    /**
     * Adds saved ingredient into the ingredient list.
     *
     * @param listDataComponents The details of the ingredient.
     */
    protected void addSavedIngredient(String[] listDataComponents) {
        int quantity = parseInt(listDataComponents[1].substring(4).trim());
        LocalDate expiry = LocalDate.parse(listDataComponents[2].trim());
        Ingredient savedIngredient = new Ingredient(listDataComponents[0], expiry, quantity);
        ingredientList.addIngredient(savedIngredient);
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

    protected void addSavedShopIngredient(String[] shopDataComponents) {
        int quantity = parseInt(shopDataComponents[1].substring(4).trim());
        Ingredient savedIngredient = new Ingredient(shopDataComponents[0], quantity);
        shoppingList.addIngredient(savedIngredient);
    }

    /**
     * Updates the ingredients list text file.
     *
     * @param ingredients The current list of ingredients.
     * @throws IOException The error thrown from file IO operations.
     */
    public void updateListFile(ArrayList<Ingredient> ingredients) throws IOException {
        FileWriter fileWriter = new FileWriter(listFilePath);
        for (Ingredient ingredient : ingredients) {
            assert ingredient != null : "Ingredient must not be null!";
            fileWriter.write(ingredient.saveFormat());
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
     * @param ingredients The current list of ingredients in the shopping list.
     * @throws IOException The error thrown from file IO operations.
     */
    public void updateShopFile(ArrayList<Ingredient> ingredients) throws IOException {
        FileWriter fileWriter = new FileWriter(shopFilePath);
        for (Ingredient ingredient : ingredients) {
            fileWriter.write(ingredient.toShopFormat());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Updates all the text files.
     *
     * @param storedIngredients The current list of ingredients.
     * @param shoppingIngredients The current shopping list of ingredients.
     * @param notification Notification object.
     */
    public void updateFiles(ArrayList<Ingredient> storedIngredients, ArrayList<Ingredient> shoppingIngredients
            , Notification notification) {
        try {
            updateListFile(storedIngredients);
            updateLogFile(notification);
            updateShopFile(shoppingIngredients);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error while trying to update ingredient list file.");
        }
    }
}
