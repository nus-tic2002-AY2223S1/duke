package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InputWrongPriorityExceptionTest {

    @Test
    public void InputWrongPriorityExceptionConstructorTest() {
        InputWrongPriorityException inputWrongPriorityExceptions = new InputWrongPriorityException();
        assertEquals(" ☹ OOPS!!! Priority can only be either low, medium, or high", inputWrongPriorityExceptions.errorMsg);
    }

    @Test
    public void InputWrongPriorityExceptionAnotherConstructorTest() {
        InputWrongPriorityException inputWrongPriorityExceptions = new InputWrongPriorityException();
        assertNotEquals("This is a wrong message", inputWrongPriorityExceptions.errorMsg);
    }

    @Test
    public void InputWrongPriorityExceptionLastConstructorTest() {
        InputWrongPriorityException inputWrongPriorityExceptions = new InputWrongPriorityException();
        assertNotEquals("This is another wrong message", inputWrongPriorityExceptions.errorMsg);
    }
}
