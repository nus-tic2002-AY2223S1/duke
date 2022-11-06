package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorCommandTest {

    @Test
    public void ErrorCommandInputCommandTest() {
        ErrorCommand errorCommand = new ErrorCommand("Find Task");
        assertEquals("Find Task", errorCommand.inputCommand);
    }

    @Test
    public void ErrorCommandAnotherInputCommandTest() {
        ErrorCommand errorCommand = new ErrorCommand("one 3");
        assertEquals("one 3", errorCommand.inputCommand);
    }

    @Test
    public void ErrorCommandLastInputCommandTest() {
        ErrorCommand errorCommand = new ErrorCommand("deletethis");
        assertEquals("deletethis", errorCommand.inputCommand);
    }
}
