package seedu.storage;

import org.junit.jupiter.api.Test;
import seedu.data.exception.FridgetException;
import seedu.notification.Notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private static final String FILE_PATH_LIST = "config/savedList.txt";
    private static final String FILE_PATH_LOGS = "config/savedLogs.txt";
    private static final String REGEX_DATA_SEPARATOR = " \\| ";

    IngredientList ingredientList = new IngredientList();
    Notification notification = new Notification();
    Storage storage = new Storage(ingredientList, notification, FILE_PATH_LIST, FILE_PATH_LOGS);

    @Test
    void addSavedIngredient_correctSavedIngredientFormat_expectCorrectFormatInIngredientList() {
        String savedIngredient = "chicken | Qty: 1 | 2021-10-15";
        String[] dataComponents = savedIngredient.split(REGEX_DATA_SEPARATOR);
        storage.addSavedIngredient(dataComponents);
        try {
            String actualSaveFormat = ingredientList.getIngredientList("default").get(0).saveFormat();
            assertEquals(actualSaveFormat, savedIngredient);
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void addSavedNotification_correctSavedLogFormat_expectNotificationOn() {
        String savedNotification = "2021-10-11 14:38 | yes";
        storage.addSavedNotification(savedNotification);

        assertTrue(Notification.isNotificationOn());
    }
}
