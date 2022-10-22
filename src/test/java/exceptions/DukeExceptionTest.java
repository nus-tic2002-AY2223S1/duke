package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {

    @Test
    public void DukeExceptionConstructorTest() {
        DukeException dukeException = new DukeException("This is a wrong msg");
        assertEquals("This is a wrong msg", dukeException.errorMsg);
    }

    @Test
    public void DukeExceptionGetMessageTest() {
        DukeException dukeException = new DukeException("This is a wrong msg");
        assertEquals("This is a wrong msg", dukeException.getMessage());
    }

    @Test
    public void DukeExceptionAnotherGetMessageTest() {
        DukeException dukeException = new DukeException("Please input another command");
        assertEquals("Please input another command", dukeException.getMessage());
    }
}
