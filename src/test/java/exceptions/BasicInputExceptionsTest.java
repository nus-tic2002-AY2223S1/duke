package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BasicInputExceptionsTest {

    @Test
    public void BasicInputExceptionConstructorTest() {
        BasicInputException basicInputException = new BasicInputException();
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + "I only accept these commands {\"bye\", \"mark\", \"unmark\", \"deadline\", \"todo\", \"list\", \"delete\", \"event\", \"findtask\", \"priority\", \"find\", \"dowithinperiod\"}", basicInputException.errorMsg);
    }

    @Test
    public void BasicInputExceptionAnotherConstructorTest() {
        BasicInputException basicInputException = new BasicInputException();
        assertNotEquals("This is a wrong message", basicInputException.errorMsg);
    }

    @Test
    public void BasicInputExceptionLastConstructorTest() {
        BasicInputException basicInputException = new BasicInputException();
        assertNotEquals("This is another wrong message", basicInputException.errorMsg);
    }
}
