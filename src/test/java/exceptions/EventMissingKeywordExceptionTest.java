package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EventMissingKeywordExceptionTest {

    @Test
    public void DeadlineMissDeadlineMissingKeywordExceptionConstructorTest() {
        EventMissingKeywordException eventMissingKeywordException = new EventMissingKeywordException();
        assertEquals(" ☹ OOPS!!! The key word /at must exists in the event command.", eventMissingKeywordException.errorMsg);
    }

    @Test
    public void DeadlineMissDeadlineMissingKeywordExceptionAnotherConstructorTest() {
        EventMissingKeywordException eventMissingKeywordException = new EventMissingKeywordException();
        assertNotEquals("This is a wrong message", eventMissingKeywordException.errorMsg);
    }

    @Test
    public void DeadlineMissDeadlineMissingKeywordExceptionLastConstructorTest() {
        EventMissingKeywordException eventMissingKeywordException = new EventMissingKeywordException();
        assertNotEquals("This is another wrong message", eventMissingKeywordException.errorMsg);
    }
}
