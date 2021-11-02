package seedu.item;

import org.junit.jupiter.api.Test;
import seedu.data.item.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    public static final String DUMMY_NAME = "dummyItem";
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    public static final LocalDate FAR_FUTURE = LocalDate.now().plusYears(5);
    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

    @Test
    void isNearExpiry_expiringDummyItem_expectTrue() {
        Item expiringDummy = new Item(DUMMY_NAME, TOMORROW);
        assertTrue(expiringDummy.isNearExpiry());
    }

    @Test
    void isNearExpiry_notExpiringDummyItem_expectFalse() {
        Item expiringDummy = new Item(DUMMY_NAME, FAR_FUTURE);
        assertFalse(expiringDummy.isNearExpiry());
    }

    @Test
    void isPastExpiry_expiringDummyItem_expectFalse() {
        Item expiringDummy = new Item(DUMMY_NAME, TOMORROW);
        assertFalse(expiringDummy.isPastExpiry());
    }

    @Test
    void isPastExpiry_expiredDummyItem_expectTrue() {
        Item expiringDummy = new Item(DUMMY_NAME, YESTERDAY);
        assertTrue(expiringDummy.isPastExpiry());
    }

    @Test
    void getColoredExpiryDate_ExpiringItem_expectOrangeFormattedExpiry() {
        Item expiringDummy = new Item(DUMMY_NAME, TOMORROW);
        String date = TOMORROW.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        // String expected = String.format("\u001B[33m%s\u001B[0m", date);
        String expected = date;
        assertEquals(expected, expiringDummy.getColoredExpiryDate());
    }

    @Test
    void toString_expiredDummyItem_expectCorrectStringFormat() {
        LocalDate expiredDate = LocalDate.parse("1212-12-12");
        Item expiredDummy = new Item(DUMMY_NAME, expiredDate);
        // assertEquals("dummyItem | Qty: 1 | \u001B[31m12 Dec 1212\u001B[0m", expiredDummy.toString());
        assertEquals("dummyItem | Qty: 1 | 12 Dec 1212", expiredDummy.toString());
    }

    @Test
    void saveFormat_dummyItem_expectCorrectSaveFormat() {
        Item dummy = new Item(DUMMY_NAME, YESTERDAY);
        String correctOutput = "dummyItem | Qty: 1 | " + YESTERDAY;
        assertEquals(correctOutput, dummy.saveFormat());
    }

    @Test
    void ingNameComparator_itemAB_expectAscendingTrue() {
        Item a = new Item("A", TOMORROW);
        Item b = new Item("B", YESTERDAY);
        int expected = "A".compareTo("B");
        int actual = Item.IngNameComparator.compare(a, b);
        assertEquals(expected, actual);
    }

    @Test
    void ingExpiryComparator_compareFromAToB_expectDescendingTrue() {
        Item a = new Item("A", TOMORROW);
        Item b = new Item("B", YESTERDAY);
        int expected = TOMORROW.compareTo(YESTERDAY);
        int actual = Item.IngExpiryComparator.compare(a, b);
        assertEquals(expected, actual);
    }
}
