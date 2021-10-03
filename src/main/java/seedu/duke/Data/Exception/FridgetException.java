package seedu.duke.Data.Exception;

public class FridgetException extends Exception {
    /**
     * A general exception in Fridget.
     * @param message text to describe what kind of exception is thrown.
     */
    public FridgetException(String message) {
        super(message);
    }
}
