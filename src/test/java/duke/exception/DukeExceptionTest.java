package duke.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DukeExceptionTest {

    @Test
    void testDukeException() {
        DukeException exception = new DukeException("error message");
        Assertions.assertEquals("error code: 500, message: error message", exception.getMessage());
    }
}
