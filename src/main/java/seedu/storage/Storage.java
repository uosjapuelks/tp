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
    private final Notification notification;
    private final String fileDirectory;
    private final String listFilePath;
    private final String logFilePath;

    private static final Logger logger = Logger.getLogger("logger");
    private static final String REGEX_DATA_SEPARATOR = " \\| ";

    /**
     * A constructor to save data into text file.
     *
     * @param listFilePath pathway of ingredient list file storage.
     * @param logFilePath pathway of user log file storage.
     */
    public Storage(IngredientList ingredientList, Notification notification, String listFilePath, String logFilePath) {
        String[] fileComponents = listFilePath.split("/");
        this.ingredientList = ingredientList;
        this.notification = notification;
        this.fileDirectory = fileComponents[0];
        this.listFilePath = listFilePath;
        this.logFilePath = logFilePath;

        try {
            loadFile();
        } catch (IOException e) {
            logger.log(Level.WARNING, "STORAGE: unable to load existing file");
            System.out.println("Error while trying to load existing file.");
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

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            listFile.createNewFile();
            logFile.createNewFile();
            return;
        }

        Scanner logScanner = new Scanner(logFile);
        addSavedNotification(logScanner.nextLine());

        Scanner listScanner = new Scanner(listFile);
        while (listScanner.hasNext()) {
            String line = listScanner.nextLine();
            String[] listDataComponents = line.split(REGEX_DATA_SEPARATOR);
            addSavedIngredient(listDataComponents);
        }
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
        notification.setNotificationStatus(!splitString[1].equals("no"));
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
     * Updates the ingredients list text file.
     *
     * @param ingredients The current list of ingredients.
     * @throws IOException The error thrown from file IO operations.
     */
    public void updateListFile(ArrayList<Ingredient> ingredients) throws IOException {
        FileWriter fileWriter = new FileWriter(listFilePath);
        for (Ingredient ingredient : ingredients) {
            assert ingredient != null : "ingredient must not be null";
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
        assert notification != null : "notification must not be null";
        fileWriter.write(notification.toString());
        fileWriter.close();
    }

    /**
     * Updates all the text files.
     *
     * @param ingredients The current list of ingredients.
     */
    public void updateFiles(ArrayList<Ingredient> ingredients, Notification notification) {
        try {
            updateListFile(ingredients);
            updateLogFile(notification);
        } catch (IOException e) {
            System.out.println("Error while trying to update ingredient list file.");
        }
    }
}
