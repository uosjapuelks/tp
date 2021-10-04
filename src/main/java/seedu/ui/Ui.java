package seedu.ui;

import seedu.data.ingredient.Ingredient;

import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final String SEPARATOR_LINE = "_____________________________________";

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
        String logo = "                                                                        \n"
                + "   ad88              88           88                                    \n"
                + "  d8\"                \"\"           88                             ,d     \n"
                + "  88                              88                             88     \n"
                + "MM88MMM  8b,dPPYba,  88   ,adPPYb,88   ,adPPYb,d8   ,adPPYba,  MM88MMM  \n"
                + "  88     88P'   \"Y8  88  a8\"    `Y88  a8\"    `Y88  a8P_____88    88     \n"
                + "  88     88          88  8b       88  8b       88  8PP\"\"\"\"\"\"\"    88     \n"
                + "  88     88          88  \"8a,   ,d88  \"8a,   ,d88  \"8b,   ,aa    88,    \n"
                + "  88     88          88   `\"8bbdP\"Y8   `\"YbbdP\"Y8   `\"Ybbd8\"'    \"Y888  \n"
                + "                                       aa,    ,88                       \n"
                + "                                        \"Y8bbdP\"                        ";
        System.out.println(logo);
        System.out.println("Hello!");
        System.out.println("What would you like to do?");
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Prints a line to separate between input and output.
     */
    public void printSeparatorLine() {
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Prints a reaction to user successfully adding an ingredient.
     * @param ingredient The ingredient the user has added.
     */
    public void printReactionToAddingIngredient(Ingredient ingredient) {
        System.out.println("You have successfully added:");
        System.out.println("    " + ingredient);
    }

    /**
     * Get input from user.
     * @return A String containing user input.
     */
    public String readUserInput() {
        currentUserInput = in.nextLine();
        return currentUserInput;
    }

    /**
     * Gets stored user input.
     * @return Stored user input.
     */
    public String getCurrentUserInput() {
        return currentUserInput;
    }
}
