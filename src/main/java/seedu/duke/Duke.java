package seedu.duke;

import seedu.ui.Ui;

public class Duke {
    private final Ui ui;

    /**
     * Constructor for Fridget.
     */
    public Duke() {
        ui = new Ui();
    }

    public void run() {
        ui.printIntroduction();
        String userInput = ui.readUserInput();
        System.out.println("Hello " + userInput);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
