package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FindTaskMissingDatetimeExceptionTest {

    @Test
    public void FindTaskMissingDatetimeExceptionConstructorTest() {
        FindTaskMissingDatetimeException findTaskMissingDatetimeException = new FindTaskMissingDatetimeException();
        assertEquals("OOPS!!! The datetime of a findtask command cannot be empty.", findTaskMissingDatetimeException.errorMsg);
    }

    @Test
    public void FindTaskMissingDatetimeExceptionAnotherConstructorTest() {
        FindTaskMissingDatetimeException findTaskMissingDatetimeException = new FindTaskMissingDatetimeException();
        assertNotEquals("This is a wrong message", findTaskMissingDatetimeException.errorMsg);
    }

    @Test
    public void FindTaskMissingDatetimeExceptionLastConstructorTest() {
        FindTaskMissingDatetimeException findTaskMissingDatetimeException = new FindTaskMissingDatetimeException();
        assertNotEquals("This is another wrong message", findTaskMissingDatetimeException.errorMsg);
    }
}
