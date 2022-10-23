package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TodoMissingDescriptionExceptionTest {
    @Test
    public void TodoMissingDescriptionExceptionConstructorTest() {
        TodoMissingDescriptionException todoMissingDescriptionException = new TodoMissingDescriptionException();
        assertEquals(" ☹ OOPS!!! The description of a todo cannot be empty.", todoMissingDescriptionException.errorMsg);
    }

    @Test
    public void TodoMissingDescriptionExceptionAnotherConstructorTest() {
        TodoMissingDescriptionException todoMissingDescriptionException = new TodoMissingDescriptionException();
        assertNotEquals("This is a wrong message", todoMissingDescriptionException.errorMsg);
    }

    @Test
    public void TodoMissingDescriptionExceptionLastConstructorTest() {
        TodoMissingDescriptionException todoMissingDescriptionException = new TodoMissingDescriptionException();
        assertNotEquals("This is another wrong message", todoMissingDescriptionException.errorMsg);
    }
}
