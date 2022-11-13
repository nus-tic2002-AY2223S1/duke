package duke.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandArgsExceptionTest {

    @Test
    void testCommandArgsException() {
        CommandArgsException exception = new CommandArgsException("error message");
        Assertions.assertEquals("error code: 601, message: error message", exception.getMessage());
    }
}
