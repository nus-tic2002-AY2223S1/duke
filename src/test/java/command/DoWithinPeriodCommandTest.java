package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoWithinPeriodCommandTest {
    @Test
    public void DoWithinPeriodConstructorTest() {
        DoWithinPeriodCommand doWithinPeriodCommand = new DoWithinPeriodCommand("Write an essay", "02/03/2022", "05/10/2022");
        assertEquals("Write an essay", doWithinPeriodCommand.description);
        assertEquals("02/03/2022", doWithinPeriodCommand.startPeriodDate);
        assertEquals("05/10/2022", doWithinPeriodCommand.endPeriodDate);
    }

    @Test
    public void DoWithinPeriodAnotherConstructorTest() {
        DoWithinPeriodCommand doWithinPeriodCommand = new DoWithinPeriodCommand("Prepare for math exam", "21/03/2022", "30/03/2022");
        assertEquals("Prepare for math exam", doWithinPeriodCommand.description);
        assertEquals("21/03/2022", doWithinPeriodCommand.startPeriodDate);
        assertEquals("30/03/2022", doWithinPeriodCommand.endPeriodDate);
    }

    @Test
    public void DoWithinPeriodLastConstructorTest() {
        DoWithinPeriodCommand doWithinPeriodCommand = new DoWithinPeriodCommand("Get swimming certificate", "15/11/2022", "15/12/2022");
        assertEquals("Get swimming certificate", doWithinPeriodCommand.description);
        assertEquals("15/11/2022", doWithinPeriodCommand.startPeriodDate);
        assertEquals("15/12/2022", doWithinPeriodCommand.endPeriodDate);
    }
}