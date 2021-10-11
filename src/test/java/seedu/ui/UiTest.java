package seedu.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UiTest {
    Ui ui = new Ui();

    @Test
    void sortTypeMessage_emptySortTypeInput_expectReturnDefault() {
        String sortTypeString = "";
        assertEquals("< Listing items in Alphabetical order >", ui.sortTypeMessage(sortTypeString));
    }

    @Test
    void sortTypeMessage_rSortTypeInput_expectByLeastRecentMessage() {
        String sortTypeString = "r";
        assertEquals("< Listing Most Recently Added items last >", ui.sortTypeMessage(sortTypeString));
    }

    @Test
    void sortTypeMessage_eSortTypeInput_expectByExpiryMessage() {
        String sortTypeString = "e";
        assertEquals("< Listing earliest [Expiry Date] first >", ui.sortTypeMessage(sortTypeString));
    }
}
