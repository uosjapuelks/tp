package seedu.fridget;

import seedu.commands.Command;
import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.ui.Ui;

public class Fridget {
    private final Ui ui;
    private final Parser parser;
    private final IngredientList ingredientList;

    /**
     * Constructor for Fridget.
     */
    public Fridget() {
        ui = new Ui();
        parser = new Parser();
        ingredientList = new IngredientList();
    }

    public void run() {
        ui.printIntroduction();
        Command c = new Command();

        do {
            try {
                String userInput = ui.readUserInput();
                ui.printSeparatorLine();
                c = parser.parseCommand(userInput);
                c.execute(ui, parser, ingredientList);
            } catch (FridgetException e) {
                e.printStackTrace();
            }
        } while (c.status());

    }

    /**
     * Main entry-point for the java.fridget.Fridget application.
     */
    public static void main(String[] args) {
        new Fridget().run();
    }
}
