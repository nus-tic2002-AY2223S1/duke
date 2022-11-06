package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DoWithinPeriodWrongFormatExceptionTest {
    @Test
    public void DoWithinPeriodWrongFormatExceptionConstructorTest() {
        DoWithinPeriodWrongFormatException doWithinPeriodWrongFormatException = new DoWithinPeriodWrongFormatException();
        assertEquals("OOPS!!! Input has wrong format. DoWithinPeriod command should be: dowithinperiod {description} /between {start date} /and {end date}", doWithinPeriodWrongFormatException.errorMsg);
    }

    @Test
    public void DoWithinPeriodWrongFormatExceptionAnotherConstructorTest() {
        DoWithinPeriodWrongFormatException doWithinPeriodWrongFormatException = new DoWithinPeriodWrongFormatException();
        assertNotEquals("This is a wrong message", doWithinPeriodWrongFormatException.errorMsg);
    }

    @Test
    public void DoWithinPeriodWrongFormatExceptionLastConstructorTest() {
        DoWithinPeriodWrongFormatException doWithinPeriodWrongFormatException = new DoWithinPeriodWrongFormatException();
        assertNotEquals("This is another wrong message", doWithinPeriodWrongFormatException.errorMsg);
    }
}
