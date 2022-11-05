package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InputIndexOutOfBoundsExceptionTest {

    @Test
    public void InputIndexOutOfBoundsExceptionConstructorTest() {
        InputIndexOutOfBoundsException inputIndexOutOfBoundsException = new InputIndexOutOfBoundsException();
        assertEquals("OOPS!!! Index in the input is out of bound. Please choose an index that is within the size of our tasklist", inputIndexOutOfBoundsException.errorMsg);
    }

    @Test
    public void InputIndexOutOfBoundsExceptionAnotherConstructorTest() {
        InputIndexOutOfBoundsException inputIndexOutOfBoundsException = new InputIndexOutOfBoundsException();
        assertNotEquals("This is a wrong message", inputIndexOutOfBoundsException.errorMsg);
    }

    @Test
    public void InputIndexOutOfBoundsExceptionLastConstructorTest() {
        InputIndexOutOfBoundsException inputIndexOutOfBoundsException = new InputIndexOutOfBoundsException();
        assertNotEquals("This is another wrong message", inputIndexOutOfBoundsException.errorMsg);
    }
}
