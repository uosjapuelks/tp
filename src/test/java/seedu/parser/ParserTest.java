package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.commands.AddCommand;
import seedu.commands.ListCommand;
import seedu.commands.ResetCommand;
import seedu.commands.RemoveCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ExitCommand;
import seedu.commands.FindCommand;
import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Parser parser = new Parser();
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(AddCommand.class));
    }

    @Test
    void parseCommand_listCommandInput_expectReturnListCommand() throws FridgetException {
        String inputString = "list";
        Parser parser = new Parser();
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ListCommand.class));
    }

    @Test
    void parseCommand_exitCommandInput_expectReturnExitCommand() throws FridgetException {
        String inputString = "exit";
        Parser parser = new Parser();
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ExitCommand.class));
    }

    @Test
    void parseCommand_helpCommandInput_expectReturnHelpCommand() throws FridgetException {
        String inputString = "help";
        Parser parser = new Parser();
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(HelpCommand.class));
    }

    @Test
    void parseCommand_findCommandInput_expectReturnFindCommand() throws FridgetException {
        String inputString = "find";
        Parser parser = new Parser();
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(FindCommand.class));
    }

    @Test
    void parseCommand_removeCommandInput_expectReturnRemoveCommand() throws FridgetException {
        String inputString = "remove";
        Parser parser = new Parser();
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(RemoveCommand.class));
    }

    @Test
    void parseCommand_resetCommandInput_expectReturnResetCommand() throws FridgetException {
        String inputString = "reset";
        Parser parser = new Parser();
        Command parsedResult = parser.parseCommand(inputString);
        assertTrue(parsedResult.getClass().isAssignableFrom(ResetCommand.class));
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
}
