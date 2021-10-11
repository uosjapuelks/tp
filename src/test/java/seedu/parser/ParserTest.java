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
import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

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
    void parseIngredientForAdding_addingIngredientInput_expectReturnIngredient() throws FridgetException {
        String inputString = "add chicken /2021-10-15";
        Ingredient parsedResult = parser.parseIngredientForAdding(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(Ingredient.class));
    }

    @Test
    void parseIngredientForAdding_addingIngredientInput_exceptionThrown() {
        String inputString = "add chicken";
        assertThrows(FridgetException.class, () -> {
            parser.parseIngredientForAdding(inputString);
        });
    }

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
}
