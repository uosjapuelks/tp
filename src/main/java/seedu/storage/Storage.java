package seedu.storage;

import seedu.data.ingredient.Ingredient;
import seedu.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final IngredientList ingredientList;
    private final Parser parser;

    private final String fileDirectory;
    private final String listFilePath;
    private final String logFilePath;

    private static final String REGEX_DATA_SEPARATOR = " \\| ";

    /**
     * A constructor to save data into text file.
     * @param listFilePath pathway of ingredient list file storage.
     * @param logFilePath pathway of user log file storage.
     */
    public Storage(IngredientList ingredientList, Parser parser, String listFilePath, String logFilePath) {
        String[] fileComponents = listFilePath.split("/");
        this.ingredientList = ingredientList;
        this.parser = parser;
        this.listFilePath = listFilePath;
        this.logFilePath = logFilePath;
        this.fileDirectory = fileComponents[0];

        try {
            loadFile();
        } catch (IOException e) {
            System.out.println("Error while trying to load existing file.");
        }
    }

    private void loadFile() throws IOException {
        File dataDirectory = new File(fileDirectory);
        File listFile = new File(listFilePath);
        File logFile = new File(logFilePath);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            listFile.createNewFile();
            logFile.createNewFile();
            return;
        }

        Scanner listScanner = new Scanner(listFile);
        while (listScanner.hasNext()) {
            String line = listScanner.nextLine();
            String[] listDataComponents = line.split(REGEX_DATA_SEPARATOR);
            addSavedIngredient(listDataComponents);
        }
    }

    private void addSavedIngredient(String[] listDataComponents) {
        String addFormat = "dummy " + listDataComponents[0] + " /" + listDataComponents[1];
        Ingredient savedIngredient = parser.parseIngredientForAdding(addFormat);
        ingredientList.addIngredient(savedIngredient);
    }
}
