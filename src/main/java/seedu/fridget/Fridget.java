package seedu.fridget;

import seedu.commands.Command;
import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.Storage;
import seedu.ui.Ui;

public class Fridget {
    private final Ui ui;
    private final Parser parser;
    private final IngredientList ingredientList;
    private final Storage storage;

    /**
     * Constructor for Fridget.
     */
    public Fridget(String listFilePath, String logFilePath) {
        ui = new Ui();
        parser = new Parser();
        ingredientList = new IngredientList();
        storage = new Storage(ingredientList, parser, listFilePath, logFilePath);
    }

    public void run() {
        ui.printIntroduction();

        try {
            String userInput = ui.readUserInput();
            Command c = parser.parseCommand(userInput);
            c.execute(ui, parser, ingredientList);
            storage.updateFiles(ingredientList.getIngredientList());
        } catch (FridgetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main entry-point for the java.fridget.Fridget application.
     */
    public static void main(String[] args) {
        new Fridget("config/savedList.txt", "config/savedLog.txt").run();
    }
}
