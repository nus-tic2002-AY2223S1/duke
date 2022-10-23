package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineMissingKeywordExceptionTest {

    @Test
    public void DeadlineMissingKeywordExceptionConstructorTest() {
        DeadlineMissingKeywordException deadlineMissingKeywordException = new DeadlineMissingKeywordException();
        assertEquals(" ☹ OOPS!!! The key word /by must exists in the deadline command.", deadlineMissingKeywordException.errorMsg);
    }

    @Test
    public void DeadlineMissingKeywordExceptionAnotherConstructorTest() {
        DeadlineMissingKeywordException deadlineMissingKeywordException = new DeadlineMissingKeywordException();
        assertNotEquals("This is a wrong message", deadlineMissingKeywordException.errorMsg);
    }

    @Test
    public void DeadlineMissingKeywordExceptionLastConstructorTest() {
        DeadlineMissingKeywordException deadlineMissingKeywordException = new DeadlineMissingKeywordException();
        assertNotEquals("This is another wrong message", deadlineMissingKeywordException.errorMsg);
    }
}
