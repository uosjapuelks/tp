package seedu.parser;

import seedu.commands.AddCommand;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.ExpiringCommand;
import seedu.commands.FindCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.NotificationCommand;
import seedu.commands.RemoveCommand;
import seedu.commands.ResetCommand;
import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    public enum CommandType {
        ADD,
        REMOVE,
        FIND
    }

    /**
     * Constructor for parser.
     */
    public Parser() {
    }

    /**
     * Returns a Command class based on user input.
     *
     * @param userInput The input from the user.
     * @return Command class that user input is referencing.
     * @throws FridgetException thrown when invalid command is input.
     */
    public Command parseCommand(String userInput) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String userCommand = processedInput[0];

        switch (userCommand.toLowerCase()) {
        case "add":
            return new AddCommand();
        case "remove":
            return new RemoveCommand();
        case "list":
            return new ListCommand();
        case "expiring":
            return new ExpiringCommand();
        case "exit":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand();
        case "notifs":
            return new NotificationCommand();
        case "reset":
            return new ResetCommand();
        default:
            assert !userCommand.equalsIgnoreCase("add");
            assert !userCommand.equalsIgnoreCase("remove");
            assert !userCommand.equalsIgnoreCase("list");
            assert !userCommand.equalsIgnoreCase("expiring");
            assert !userCommand.equalsIgnoreCase("exit");
            assert !userCommand.equalsIgnoreCase("help");
            assert !userCommand.equalsIgnoreCase("find");
            assert !userCommand.equalsIgnoreCase("notifs");
            assert !userCommand.equalsIgnoreCase("reset");
            throw new FridgetException("No command found!\n"
                    + "Enter help if you need the list of available commands.");
        }
    }

    /**
     * Splits user inputs by " ".
     *
     * @param userInput String input to be split by " ".
     * @return String[] from splitting input.
     */
    private String[] splitUserInputByWhitespace(String userInput) {
        String[] splitInput = userInput.trim().split(" ", 2);
        assert splitInput.length >= 1;
        return splitInput;
    }

    /**
     * Removes unnecessary " " and "\t" from every iteration.
     *
     * @param splitInput Input that has been split.
     * @return Trimmed output.
     */
    private String[] trimContents(String[] splitInput) {
        String[] trimmedInput = new String[splitInput.length];
        for (int i = 0; i < splitInput.length; i++) {
            trimmedInput[i] = splitInput[i].trim();
        }
        return trimmedInput;
    }

    /**
     * Execute splitUserInput and trimContents on userInput.
     *
     * @param userInput String input by user.
     * @return String[] after processing using splitUserInputByWhitespace and trimContents.
     */
    private String[] processInput(String userInput) {
        String[] splitInput = splitUserInputByWhitespace(userInput);
        String[] trimmedInput = trimContents(splitInput);
        return trimmedInput;
    }

    /**
     * Extracts expiry as String to parse into LocalDate.
     *
     * @param processedInput userInput after processInput().
     * @return expiry as String in format ("yyyy-mm-dd")
     * @throws FridgetException thrown when there are is no expiry date.
     */
    private String extractExpiry(String[] processedInput) throws FridgetException {
        String expiry = "";
        for (String str : processedInput) {
            expiry = (str.contains("/")) ? str.substring(str.indexOf("/") + 1).trim() : expiry;
        }
        if (expiry.equals("")) {
            String addFormat = " Try: [add] <ITEM_NAME> /<YYYY-MM-DD>";
            throw new FridgetException("Missing expiry date." + addFormat);
        }
        return expiry;
    }

    /**
     * Extracts name or item description from processed input.
     *
     * @param processedInput userInput after processInput().
     * @return name or item description.
     * @throws FridgetException thrown when there are missing inputs, name or expiry date.
     */
    private String extractDescription(String[] processedInput, CommandType commandType) throws FridgetException {
        String correctFormat;
        switch (commandType) {
        case ADD:
            correctFormat = " Try: [add] <ITEM_NAME> /<YYYY-MM-DD>";
            break;
        case REMOVE:
            correctFormat = " Try: [remove] <ITEM_NAME>";
            break;
        case FIND:
            correctFormat = " Try: [find] <ITEM_NAME>";
            break;
        default:
            correctFormat = "";
            break;
        }

        if (processedInput.length < 2) {
            throw new FridgetException("Missing item name." + correctFormat);
        } else if (!processedInput[1].contains("/") && commandType == CommandType.ADD) {
            throw new FridgetException("Missing expiry date." + correctFormat);
        }

        if (commandType == CommandType.ADD) {
            String description = processedInput[1].substring(0, processedInput[1].indexOf("/")).trim();
            if (!description.equals("")) {
                return description;
            }
            throw new FridgetException("Missing item name." + correctFormat);
        } else {
            return processedInput[1].trim();
        }
    }

    /**
     * Returns an Ingredient based on user input.
     * @param userInput The input from the user in this manner - "add burger /2021-09-23".
     * @return An ingredient.
     */
    public Ingredient parseIngredientForAdding(String userInput) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String ingredientName = extractDescription(processedInput, CommandType.ADD);
        assert !ingredientName.isEmpty();

        String expiryString = extractExpiry(processedInput);
        assert !expiryString.isEmpty();
        try {
            LocalDate expiryDate = LocalDate.parse(expiryString);
            return new Ingredient(ingredientName, expiryDate);
        } catch (DateTimeParseException e) {
            throw new FridgetException(expiryString + " is not formatted properly.\n"
                    + "Please try this format for date:\n\n"
                    + "    /YYYY-MM-DD\n"
                    + "    Example: '... /2022-08-03");
        }
    }

    public ArrayList<Ingredient> parseMultipleIngredientsForAdding(String userInput) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String[] ingredientsInfo = processedInput[1].split(";");

        ArrayList<Ingredient> allIngredientsToBeAdded = new ArrayList<>();
        for (String ingredientInfo: ingredientsInfo) {
            Ingredient newIngredient = parseIngredientForAdding(processedInput[0] + " " + ingredientInfo);
            allIngredientsToBeAdded.add(newIngredient);
        }

        return allIngredientsToBeAdded;
    }

    /**
     * Extracts the sortType from the list command.
     *
     * @param userInput Input from user.
     * @return Either "default", "e", or "r".
     */
    public String parseSortTypeForList(String userInput) {
        String[] processedInput = processInput(userInput);
        if (processedInput.length == 1) {
            return "default";
        }
        String sortType = processedInput[1].substring(processedInput[1].indexOf("-") + 1).trim();
        assert !sortType.isEmpty();
        return sortType;
    }

    /**
     * Returns a search term provided by the "find" command.
     *
     * @param userInput The input from the user in this manner - "find burger".
     * @return The search term.
     */
    public String parseSearchTerm(String userInput, CommandType commandType) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String searchTerm = extractDescription(processedInput, commandType);
        assert !searchTerm.isEmpty();
        return searchTerm;
    }
}
