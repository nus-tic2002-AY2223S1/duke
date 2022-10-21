package duke.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandNotFoundExceptionTest {

    @Test
    void testCommandNotFoundException() {
        CommandNotFoundException exception = new CommandNotFoundException("error message");
        Assertions.assertEquals("error code: 600, message: error message", exception.getMessage());
    }
}
