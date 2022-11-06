package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputWrongFormatExceptionTest {

    @Test
    public void InputWrongFormatExceptionConstructorTest() {
        InputWrongFormatException inputWrongFormatException = new InputWrongFormatException("This is a wrong format");
        assertEquals("This is a wrong format", inputWrongFormatException.errorMsg);
    }

    @Test
    public void InputWrongFormatExceptionAnotherConstructorTest() {
        InputWrongFormatException inputWrongFormatException = new InputWrongFormatException("This is a wrong command");
        assertEquals("This is a wrong command", inputWrongFormatException.errorMsg);
    }

    @Test
    public void InputWrongFormatExceptionLastConstructorTest() {
        InputWrongFormatException inputWrongFormatException = new InputWrongFormatException("Wrong command here");
        assertEquals("Wrong command here", inputWrongFormatException.errorMsg);
    }
}
