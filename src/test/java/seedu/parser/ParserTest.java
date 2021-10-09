package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.commands.AddCommand;
import seedu.data.exception.FridgetException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    Parser parser = new Parser();

    @Test
    void parseCommand_correctAddCommandFormat_success() throws FridgetException {
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


//    @Test
//    void parseIngredientForAdding() {
//    }
//
//    @Test
//    void parseSortTypeForList() {
//    }
//
//    @Test
//    void parseSearchTermFromFinding() {
//    }
}