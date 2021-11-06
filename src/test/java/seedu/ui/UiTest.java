package seedu.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    Ui ui = new Ui();

    //@@author uosjapuelks
    @Test
    void sortTypeMessage_emptySortTypeInput_expectReturnDefault() {
        String sortTypeString = "";
        assertEquals("List sorted by item name:", ui.sortTypeMessage(sortTypeString));
    }

    @Test
    void sortTypeMessage_rSortTypeInput_expectByLeastRecentMessage() {
        String sortTypeString = "r";
        assertEquals("List sorted by earliest added:", ui.sortTypeMessage(sortTypeString));
    }

    @Test
    void sortTypeMessage_eSortTypeInput_expectByExpiryMessage() {
        String sortTypeString = "e";
        assertEquals("List sorted by expiry date:", ui.sortTypeMessage(sortTypeString));
    }
}
