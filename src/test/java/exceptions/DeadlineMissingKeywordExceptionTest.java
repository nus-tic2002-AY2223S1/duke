package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineMissingKeywordExceptionTest {

    @Test
    public void DeadlineMissDeadlineMissingKeywordExceptionConstructorTest() {
        DeadlineMissingKeywordException deadlineMissingKeywordException = new DeadlineMissingKeywordException();
        assertEquals(" ☹ OOPS!!! The key word /by must exists in the deadline command.", deadlineMissingKeywordException.errorMsg);
    }

    @Test
    public void DeadlineMissDeadlineMissingKeywordExceptionAnotherConstructorTest() {
        DeadlineMissingKeywordException deadlineMissingKeywordException = new DeadlineMissingKeywordException();
        assertNotEquals("This is a wrong message", deadlineMissingKeywordException.errorMsg);
    }

    @Test
    public void DeadlineMissDeadlineMissingKeywordExceptionLastConstructorTest() {
        DeadlineMissingKeywordException deadlineMissingKeywordException = new DeadlineMissingKeywordException();
        assertNotEquals("This is another wrong message", deadlineMissingKeywordException.errorMsg);
    }
}
