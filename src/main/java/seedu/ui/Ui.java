package seedu.ui;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private static final String SEPARATOR_LINE = "__________________________________________";
    private static final String FOUR_SPACE_INDENTATION = "    ";

    private String currentUserInput;

    /**
     * A constructor to initialise ui.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints introductory message when Fridget runs.
     */
    public void printIntroduction() {
        String logo = "\n"
                + "   ad88              88           88\n"
                + "  d8\"                \"\"           88                             ,d\n"
                + "  88                              88                             88\n"
                + "MM88MMM  8b,dPPYba,  88   ,adPPYb,88   ,adPPYb,d8   ,adPPYba,  MM88MMM\n"
                + "  88     88P'   \"Y8  88  a8\"    `Y88  a8\"    `Y88  a8P_____88    88\n"
                + "  88     88          88  8b       88  8b       88  8PP\"\"\"\"\"\"\"    88\n"
                + "  88     88          88  \"8a,   ,d88  \"8a,   ,d88  \"8b,   ,aa    88,\n"
                + "  88     88          88   `\"8bbdP\"Y8   `\"YbbdP\"Y8   `\"Ybbd8\"'    \"Y888\n"
                + "                                       aa,    ,88\n"
                + "                                        \"Y8bbdP\"\n";
        String greeting = "Hello!\n"
                + "What would you like to do?";
        String introMessage = logo
                + greeting;
        printLine(introMessage);
        printSeparatorLine();
    }

    /**
     * Prints a line to separate between input and output.
     */
    public void printSeparatorLine() {
        System.out.println(SEPARATOR_LINE);
    }

    public void printLine(String content) {
        System.out.println(content);
    }

    /**
     * Prints a reaction to user successfully adding an ingredient.
     * @param ingredient The ingredient the user has added.
     */
    public void printReactionToAddingIngredient(Ingredient ingredient) {
        String acknowledgeAdd = "You have successfully added:\n";
        String addReaction = acknowledgeAdd
                + FOUR_SPACE_INDENTATION + ingredient;
        printLine(addReaction);
    }

    /**
     * Prints a reaction to user successfully removing an ingredient.
     * @param ingredient The ingredient the user has added.
     */
    public void printReactionToRemovingIngredient(Ingredient ingredient) {
        String acknowledgeRemove = "You have successfully removed:\n";
        String addReaction = acknowledgeRemove
                + FOUR_SPACE_INDENTATION + ingredient;
        printLine(addReaction);
    }

    /**
     * Returns the item the user wants to remove from Fridget.
     * @param matchingItems The list of items which match the user's search term.
     * @return The item that the user wants to remove.
     * @throws FridgetException if the user types a wrong value (non-integer or outside of index of matchingItems)
     */
    public Ingredient getItemToBeRemoved(ArrayList<Ingredient> matchingItems) throws FridgetException {
        printLine("Which item would you like to be removed? Type the index of the item below.");
        printListOfIngredients(matchingItems,true);
        printSeparatorLine();

        String userInput = readUserInput();
        printSeparatorLine();

        if (!(userInput.matches("\\d"))) {
            throw new FridgetException("No valid number was stated. The remove command has been shutdown.");
        }

        int index = Integer.parseInt(userInput);

        if (index < 0 | index > matchingItems.size()) {
            throw new FridgetException("This index is not valid. The remove command has been shutdown.");
        }

        return matchingItems.get(index - 1);
    }

    /**
     * Get input from user.
     *
     * @return A String containing user input.
     */
    public String readUserInput() {
        currentUserInput = in.nextLine();
        return currentUserInput;
    }

    /**
     * Gets stored user input.
     *
     * @return Stored user input.
     */
    public String getCurrentUserInput() {
        return currentUserInput;
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        String reassureUser = "We'll help you remember everything you told us :)\n"
                + "See you again!~~";
        printLine(reassureUser);
    }

    /**
     * Prints a list of ingredients, indented by four spaces and preceded by an index.
     *
     * @param listOfIngredients The list of ingredients to be printed.
     */
    public void printListOfIngredients(ArrayList<Ingredient> listOfIngredients, boolean hasIndex) {
        int index = 1;
        for (Ingredient ingredient : listOfIngredients) {
            String beforeIngredient = FOUR_SPACE_INDENTATION + (hasIndex ? index + ". " : "");
            printLine(beforeIngredient + ingredient);
            index++;
        }
    }

    /**
     * Prints a message informing user on list being printed.
     *
     * @param listOfIngredients The list of ingredients of all items in fridget.
     */
    public void printListMessage(ArrayList<Ingredient> listOfIngredients, String sortType) {
        String listMessage = "Here are the list of items in your fridge:\n";
        listMessage += sortTypeMessage(sortType);
        printLine(listMessage);
        printListOfIngredients(listOfIngredients, true);
    }

    /**
     * Determine sortType message.
     *
     * @param sortType "e", "r", or "default".
     * @return sortTypeMessage.
     */
    public String sortTypeMessage(String sortType) {
        switch (sortType) {
        case "e":
            return ("< Listing earliest [Expiry Date] first >");
        case "r":
            return ("< Listing Most Recently Added items first >");
        default:
            return ("< Listing items in Alphabetical order >");
        }
    }

    /**
     * Prints a list of matching ingredient for the find command.
     *
     * @param listOfIngredients The list of matching ingredients to print.
     */
    public void printListOfMatchingIngredients(ArrayList<Ingredient> listOfIngredients) {
        if (listOfIngredients.isEmpty()) {
            String noMatchingTasks = "No matching tasks found!";
            printLine(noMatchingTasks);
        } else {
            String resultsHeader = "These are the matching ingredients:";
            printLine(resultsHeader);
            printListOfIngredients(listOfIngredients, true);
        }
    }

    /**
     * Prints a reconfirm message and gets the reconfirm result.
     *
     * @return Boolean representing reconfirm status (y: confirm, n: abort)
     */
    public boolean getResetReconfirm() {
        printLine("Are you sure you want to reset everything in the ingredient list? (Y/N)");
        printSeparatorLine();
        String confirm = readUserInput();
        if (!confirm.equalsIgnoreCase("y")) {
            printSeparatorLine();
            printLine("Abort reset command.");
        }
        return confirm.equalsIgnoreCase("y");
    }

    /**
     * Prints the reset message.
     */
    public void printResetMessage() {
        printSeparatorLine();
        printLine("Ingredient list has been reset successfully.");
    }
}
