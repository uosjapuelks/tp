package seedu.storage;

public class Storage {
    private final String filePath;

    /**
     * A constructor to save data into text file.
     * @param filePath pathway of file storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
}
