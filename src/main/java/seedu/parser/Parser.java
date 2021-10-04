package seedu.parser;

import seedu.data.exception.FridgetException;

public class Parser {
    /**
     * Constructor for parser.
     */
    public Parser() {
    }

    /**
     * Parse user inputs to run its respective commands.
     * @param userInput inputs from user.
     */
    public void parseCommand(String userInput) {
        String[] splitCommand = userInput.trim().split(" ", 2);
    }
}
