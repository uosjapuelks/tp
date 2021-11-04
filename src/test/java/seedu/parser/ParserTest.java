package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.commands.AddCommand;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.ExpiringCommand;
import seedu.commands.FindCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.NotificationCommand;
import seedu.commands.RemoveCommand;
import seedu.commands.ResetCommand;
import seedu.commands.ShopListCommand;
import seedu.commands.ShopResetCommand;
import seedu.commands.UpdateCommand;
import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    Parser parser = new Parser();

    @Test
    void parseCommand_correctAddCommandFormat_success() {
        try {
            String inputString = "add burger /2021-12-12";
            assertTrue(parser.parseCommand(inputString) instanceof AddCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseCommand_noCommandWordInInput_exceptionThrown() {
        assertThrows(FridgetException.class, () -> {
            parser.parseCommand("complete nonsense");
        });
    }

    //@@author zonglun99
    @Test
    void parseCommand_addCommandInput_expectReturnAddCommand() throws FridgetException {
        String inputString = "add";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(AddCommand.class));
    }

    @Test
    void parseCommand_listCommandInput_expectReturnListCommand() throws FridgetException {
        String inputString = "list";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ListCommand.class));
    }

    @Test
    void parseCommand_exitCommandInput_expectReturnExitCommand() throws FridgetException {
        String inputString = "exit";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ExitCommand.class));
    }

    @Test
    void parseCommand_helpCommandInput_expectReturnHelpCommand() throws FridgetException {
        String inputString = "help";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(HelpCommand.class));
    }

    @Test
    void parseCommand_findCommandInput_expectReturnFindCommand() throws FridgetException {
        String inputString = "find";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(FindCommand.class));
    }

    @Test
    void parseCommand_removeCommandInput_expectReturnRemoveCommand() throws FridgetException {
        String inputString = "remove";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(RemoveCommand.class));
    }

    @Test
    void parseCommand_resetCommandInput_expectReturnResetCommand() throws FridgetException {
        String inputString = "reset";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ResetCommand.class));
    }

    @Test
    void parseCommand_expiringCommandInput_expectReturnExpiringCommand() throws FridgetException {
        String inputString = "expiring";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ExpiringCommand.class));
    }

    @Test
    void parseCommand_notificationCommandInput_expectReturnNotificationCommand() throws FridgetException {
        String inputString = "notifs";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(NotificationCommand.class));
    }

    @Test
    void parseCommand_updateCommandInput_expectReturnUpdateCommand() throws FridgetException {
        String inputString = "update";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(UpdateCommand.class));
    }

    @Test
    void parseCommand_shopListCommandInput_expectReturnShopListCommand() throws FridgetException {
        String inputString = "shoplist";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ShopListCommand.class));
    }

    @Test
    void parseCommand_shopResetCommandInput_expectReturnShopResetCommand() throws FridgetException {
        String inputString = "shopreset";
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ShopResetCommand.class));
    }

    @Test
    void parseItemForAdding_addingItemInput_expectReturnItem() throws FridgetException {
        String inputString = "add chicken /2023-10-15";
        Item parsedResult = parser.parseItemForAdding(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(Item.class));
    }
    //@@author zonglun99

    @Test
    void parseItemForAdding_addingItemInput_exceptionThrown() {
        String inputString = "add chicken";
        assertThrows(FridgetException.class, () -> {
            parser.parseItemForAdding(inputString);
        });
    }

    //@@author zonglun99
    @Test
    void parseSortTypeForList_sortTypeE_expectSortTypeEqualsE() {
        String inputString = "list -e";
        String parsedResult = parser.parseSortTypeForList(inputString);
        assertEquals(parsedResult, "e");
    }

    @Test
    void parseSortTypeForList_sortTypeR_expectSortTypeEqualsR() {
        String inputString = "list -r";
        String parsedResult = parser.parseSortTypeForList(inputString);
        assertEquals(parsedResult, "r");
    }

    @Test
    void parseSortTypeForList_sortTypeDefault_expectSortTypeEqualsDefault() {
        String inputString = "list";
        String parsedResult = parser.parseSortTypeForList(inputString);
        assertEquals(parsedResult, "default");
    }
    //@@author zonglun99
}
