package seedu.fridget;

import org.fusesource.jansi.AnsiConsole;

import seedu.commands.Command;
import seedu.data.exception.FridgetException;
import seedu.notification.Notification;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.storage.Storage;
import seedu.ui.Ui;

import java.time.format.DateTimeParseException;

public class Fridget {
    private static final String FILE_PATH_LIST = "fridgetData/savedList.txt";
    private static final String FILE_PATH_LOGS = "fridgetData/savedLogs.txt";
    private static final String FILE_PATH_SHOP = "fridgetData/savedShop.txt";

    private final Ui ui;
    private final Parser parser;
    private final IngredientList ingredientList;
    private final ShoppingList shoppingList;
    private final Notification notification;
    private final Storage storage;

    /**
     * Constructor for Fridget.
     */
    public Fridget(String listFilePath, String logFilePath, String shopFilePath) {
        ui = new Ui();
        parser = new Parser();
        ingredientList = new IngredientList();
        shoppingList = new ShoppingList();
        notification = new Notification();
        storage = new Storage(ingredientList, shoppingList, notification, listFilePath, logFilePath, shopFilePath);
    }

    public void run() {
        AnsiConsole.systemInstall();
        ui.printIntroduction();
        Command command = new Command();
        do {
            try {
                notification.printNotification(ingredientList.getIngredientList("r"));
                ui.printUserInputMessage();
                String userInput = ui.readUserInput();
                ui.printSeparatorLine();
                command = parser.parseCommand(userInput);
                command.execute(ui, parser, ingredientList, shoppingList);
                storage.updateFiles(ingredientList.getIngredientList("r"), shoppingList.getShoppingList(),
                        notification);
            } catch (FridgetException e) {
                ui.printLine(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printDateTimeFormat();
            } finally {
                ui.printSeparatorLine();
            }
        } while (command.exitNotRequired());
    }

    /**
     * Main entry-point for the java.fridget.Fridget application.
     */
    public static void main(String[] args) {
        new Fridget(FILE_PATH_LIST, FILE_PATH_LOGS, FILE_PATH_SHOP).run();
    }
}
