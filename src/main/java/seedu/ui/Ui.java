package seedu.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner in;

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
        String logo = "                                                                        \n" +
                "   ad88              88           88                                    \n" +
                "  d8\"                \"\"           88                             ,d     \n" +
                "  88                              88                             88     \n" +
                "MM88MMM  8b,dPPYba,  88   ,adPPYb,88   ,adPPYb,d8   ,adPPYba,  MM88MMM  \n" +
                "  88     88P'   \"Y8  88  a8\"    `Y88  a8\"    `Y88  a8P_____88    88     \n" +
                "  88     88          88  8b       88  8b       88  8PP\"\"\"\"\"\"\"    88     \n" +
                "  88     88          88  \"8a,   ,d88  \"8a,   ,d88  \"8b,   ,aa    88,    \n" +
                "  88     88          88   `\"8bbdP\"Y8   `\"YbbdP\"Y8   `\"Ybbd8\"'    \"Y888  \n" +
                "                                       aa,    ,88                       \n" +
                "                                        \"Y8bbdP\"                        ";
        System.out.println(logo);
        System.out.println("Hello!");
        System.out.println("What is your name?");
    }

    /**
     * Get input from user.
     * @return A String containing user input.
     */
    public String readUserInput() {
        return in.nextLine();
    }
}
