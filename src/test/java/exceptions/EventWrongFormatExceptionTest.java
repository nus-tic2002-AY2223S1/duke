package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EventWrongFormatExceptionTest {

    @Test
    public void EventWrongFormatExceptionConstructorTest() {
        EventWrongFormatException eventWrongFormatException = new EventWrongFormatException();
        assertEquals(" ☹ OOPS!!! Input has wrong format. Event command should be: event {description} /at {date}", eventWrongFormatException.errorMsg);
    }

    @Test
    public void EventWrongFormatExceptionAnotherConstructorTest() {
        EventWrongFormatException eventWrongFormatException = new EventWrongFormatException();
        assertNotEquals("This is a wrong message", eventWrongFormatException.errorMsg);
    }

    @Test
    public void EventWrongFormatExceptionLastConstructorTest() {
        EventWrongFormatException eventWrongFormatException = new EventWrongFormatException();
        assertNotEquals("This is another wrong message", eventWrongFormatException.errorMsg);
    }
}
