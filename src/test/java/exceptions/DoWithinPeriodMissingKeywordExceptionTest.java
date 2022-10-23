package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DoWithinPeriodMissingKeywordExceptionTest {

    @Test
    public void DoWithinPeriodMissingKeywordExceptionConstructorTest() {
        DoWithinPeriodMissingKeywordException doWithinPeriodMissingKeywordException = new DoWithinPeriodMissingKeywordException("/between");
        assertEquals(" ☹ OOPS!!! The key word /between must exists in the dowithinperiod command.", doWithinPeriodMissingKeywordException.errorMsg);
    }

    @Test
    public void DoWithinPeriodMissingKeywordExceptionAnotherConstructorTest() {
        DoWithinPeriodMissingKeywordException doWithinPeriodMissingKeywordException = new DoWithinPeriodMissingKeywordException("/and");
        assertEquals(" ☹ OOPS!!! The key word /and must exists in the dowithinperiod command.", doWithinPeriodMissingKeywordException.errorMsg);
    }

    @Test
    public void DoWithinPeriodMissingKeywordExceptionLastConstructorTest() {
        DoWithinPeriodMissingKeywordException doWithinPeriodMissingKeywordException = new DoWithinPeriodMissingKeywordException("/and");
        assertNotEquals("This is another wrong message", doWithinPeriodMissingKeywordException.errorMsg);
    }
}
