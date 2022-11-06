package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineWrongFormatExceptionTest {

    @Test
    public void DeadlineWrongFormatExceptionConstructorTest() {
        DeadlineWrongFormatException deadlineWrongFormatException = new DeadlineWrongFormatException();
        assertEquals("OOPS!!! Input has wrong format. Deadline command should be: deadline {description} /by {deadline}", deadlineWrongFormatException.errorMsg);
    }

    @Test
    public void DeadlineWrongFormatExceptionAnotherConstructorTest() {
        DeadlineWrongFormatException deadlineWrongFormatException = new DeadlineWrongFormatException();
        assertNotEquals("This is a wrong message", deadlineWrongFormatException.errorMsg);
    }

    @Test
    public void DeadlineWrongFormatExceptionLastConstructorTest() {
        DeadlineWrongFormatException deadlineWrongFormatException = new DeadlineWrongFormatException();
        assertNotEquals("This is another wrong message", deadlineWrongFormatException.errorMsg);
    }
}
