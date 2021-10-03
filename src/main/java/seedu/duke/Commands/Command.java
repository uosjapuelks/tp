package seedu.duke.Commands;

import seedu.duke.Data.Exception.FridgetException;

public abstract class Command {
    /**
     * Constructor for Command
     */
    public Command() {
    }

    /**
     * Executes the command.
     * @throws FridgetException exception thrown when Command class is executed.
     */
    public void execute() throws FridgetException {
        throw new FridgetException("Method is unspecified");
    }
}
