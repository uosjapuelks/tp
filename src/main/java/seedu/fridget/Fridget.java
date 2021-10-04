package seedu.fridget;

import seedu.ui.Ui;

public class Fridget {
    private final Ui ui;

    /**
     * Constructor for Fridget.
     */
    public Fridget() {
        ui = new Ui();
    }

    public void run() {
        ui.printIntroduction();
        String userInput = ui.readUserInput();
        System.out.println("Hello " + userInput);
    }

    /**
     * Main entry-point for the java.duke.Fridget application.
     */
    public static void main(String[] args) {
        new Fridget().run();
    }
}
