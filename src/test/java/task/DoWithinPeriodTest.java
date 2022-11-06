package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoWithinPeriodTest {
    @Test
    public void DoWithinPeriodConstructorTest() {
        DoWithinPeriod doWithinPeriod = new DoWithinPeriod("Finish math homework", "3 June 2023", "4 June 2023");
        assertEquals("Finish math homework", doWithinPeriod.getDescription());
    }

    @Test
    public void DoWithinPeriodToStringTest() {
        DoWithinPeriod doWithinPeriod = new DoWithinPeriod("Finish math homework", "3 June 2023", "4 June 2023");
        assertEquals("[DWP][ ] Finish math homework (between 3 June 2023 and 4 June 2023) [low]", doWithinPeriod.toString());
    }

    @Test
    public void DoWithinPeriodStringToOutput() {
        DoWithinPeriod doWithinPeriod = new DoWithinPeriod("Finish math homework", "3 June 2023", "4 June 2023");
        assertEquals("DWP | 0 | Finish math homework | 3 June 2023 | 4 June 2023 | low", doWithinPeriod.stringToOutput());
    }
}
