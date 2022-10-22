package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputNumberFormatExceptionTest {
    @Test
    public void InputNumberFormatExceptionConstructorTest() {
        InputNumberFormatException inputNumberFormatException = new InputNumberFormatException("mark here");
        assertEquals(" ☹ OOPS!!! The index after mark is not numeric, please enter an numeric number.", inputNumberFormatException.errorMsg);
    }

    @Test
    public void InputNumberFormatExceptionAnotherConstructorTest() {
        InputNumberFormatException inputNumberFormatException = new InputNumberFormatException("unmark test");
        assertEquals(" ☹ OOPS!!! The index after unmark is not numeric, please enter an numeric number.", inputNumberFormatException.errorMsg);
    }

    @Test
    public void InputNumberFormatExceptionLastConstructorTest() {
        InputNumberFormatException inputNumberFormatException = new InputNumberFormatException("delete this");
        assertEquals(" ☹ OOPS!!! The index after delete is not numeric, please enter an numeric number.", inputNumberFormatException.errorMsg);
    }
}