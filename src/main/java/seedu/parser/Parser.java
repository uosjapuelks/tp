package seedu.parser;

import seedu.commands.AddCommand;
import seedu.commands.ExitCommand;
import seedu.commands.Command;
import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

import java.time.LocalDate;

public class Parser {
    /**
     * Constructor for parser.
     */
    public Parser() {
    }

    /**
     * Returns a Command class based on user input.
     * @param userInput The input from the user.
     * @return Command class that user input is referencing.
     */
    public Command parseCommand(String userInput) throws FridgetException {
        String userCommand = userInput.trim().split(" ", 2)[0];

        switch (userCommand) {
        case "add":
            return new AddCommand();
        case "exit":
            return new ExitCommand();
        default:
            throw new FridgetException("No command found!");
        }
    }

    /**
     * Returns a Ingredient based on user input.
     * @param userInput The input from the user in this manner - "add burger /2021-09-23"
     * @return An ingredient
     */
    public Ingredient parseIngredientForAdding(String userInput) {
        String[] splitUserInput = userInput.trim().split(" ");
        String ingredientName = splitUserInput[1];
        LocalDate expiryDate = LocalDate.parse(splitUserInput[2].replace("/", ""));
        return new Ingredient(ingredientName, expiryDate);
    }
}
