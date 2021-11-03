package seedu.storage;

import org.junit.jupiter.api.Test;
import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class ItemListTest {
    private final LocalDate today = LocalDate.now();
    private final Item itemAExpireTomorrow = new Item("aaa", today.plusDays(1));
    private final Item itemBExpireToday = new Item("bbb", today);
    private final Item itemCExpireYesterday = new Item("ccc", today.minusDays(1));
    private final ItemList testList = new ItemList();
    private final ArrayList<Item> expectedList = new ArrayList<Item>();
    private final ItemList itemList = new ItemList();

    private void fillExpectedList(Item a, Item b, Item c) {
        expectedList.add(a);
        expectedList.add(b);
        expectedList.add(c);
    }

    private ItemList filledTestList() {
        testList.addItem(itemCExpireYesterday);
        testList.addItem(itemAExpireTomorrow);
        testList.addItem(itemBExpireToday);
        return testList;
    }

    @Test
    void containsItem_aInTestList_expectedResultTrue() {
        testList.addItem(itemAExpireTomorrow);
        assertTrue(testList.containsItem(itemAExpireTomorrow));
    }

    @Test
    void containsItem_aNotInTestList_expectedResultTrue() {
        testList.addItem(itemCExpireYesterday);
        assertFalse(testList.containsItem(itemAExpireTomorrow));
    }

    @Test
    void searchItemNameExist_itemInTestList_expectedResultTrue() {
        Item itemA = new Item("burger", today.plusDays(1));
        Item itemB = new Item("Burger", today.minusDays(1));
        itemList.addItem(itemA);
        assertTrue(itemList.searchItemNameExist(itemB));
    }

    @Test
    void sortItem_sortByDate_expectCToBToA() {
        fillExpectedList(itemCExpireYesterday, itemBExpireToday, itemAExpireTomorrow);
        try {
            assertEquals(expectedList, filledTestList().sortItem(true));
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void sortItem_sortByName_expectAToBToC() {
        fillExpectedList(itemAExpireTomorrow, itemBExpireToday, itemCExpireYesterday);
        try {
            assertEquals(expectedList, filledTestList().sortItem(false));
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void getItem_CtoAtoBInput_expectCToAToBOutput() {
        fillExpectedList(itemCExpireYesterday, itemAExpireTomorrow, itemBExpireToday);
        try {
            assertEquals(expectedList, filledTestList().getItemList("r"));
        } catch (FridgetException e) {
            fail();
        }
    }
}