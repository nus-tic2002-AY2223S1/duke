package utils;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.utils.DateProcessor;
import duke.utils.DukeException;

public class DateProcessorTest {
    @Test
    public void dateTimeToUnixInvalidTest() {
        String s = ".1/4/1999 0000";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \".1/4/1999 0000\"");

        try {
            DateProcessor.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixPseudoValidDayTest() {
        String s = "32/12/1999 0000";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \"32/12/1999 0000\"");

        try {
            DateProcessor.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixPseudoValidMonthTest() {
        String s = "30/13/1999 0000";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \"30/13/1999 0000\"");

        try {
            DateProcessor.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixPseudoValidDayMonthTest() {
        String s = "31/02/1999 0000";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \"31/02/1999 0000\"");

        try {
            DateProcessor.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixNoTimeTest() throws DukeException {
        String s = "1/11/2022";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \"1/11/2022\"");
        try {
            DateProcessor.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixValidTest() {
        String s = "1/11/2022 0000";
        long expected = 1667232000;
        long actual;
        try {
            actual = DateProcessor.dateToUnix(s);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void dateToUnixInvalidTest() {
        String s = ".1/4/1999";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \".1/4/1999 0000\"");
        try {
            DateProcessor.dateToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateToUnixPseudoValidDayTest() {
        String s = "32/4/1999";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \"32/4/1999 0000\"");
        try {
            DateProcessor.dateToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateToUnixValidTest() {
        String s = "1/11/2022";
        long expected = 1667232000;
        long actual;
        try {
            actual = DateProcessor.dateToUnix(s);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void unixToStringValidTest() {
        long l = 1667260800;
        String expected = "Tue 01 Nov 2022, 8:00 AM";
        String actual = DateProcessor.unixToString(l);
        assertEquals(expected, actual);
    }

    @Test
    public void unixToSimplifiedStringTest() {
        long l = 1667260800;
        String expected = "08:00 AM";
        String actual = DateProcessor.unixToSimplifiedString(l);
        assertEquals(expected, actual);
    }

    @Test
    public void getMetaTimeStampTest() {
        String expected = DateProcessor.unixToSimplifiedString(Instant.now().getEpochSecond());
        String actual = DateProcessor.getMetaTimeStamp();
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeValidTest() throws DukeException {
        String s = "1/4/1999 0000";
        long expected = 922896000;
        long actual = DateProcessor.processDateTime(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeNoTimeTest() {
        String s = "1/4/1999";
        DukeException expected = new DukeException("! Invalid date/time format. Date time has to be dd/mm/yyyy HHmm.");
        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidTimeTest() {
        String s = "1/4/1999 900";
        DukeException expected = new DukeException("! Invalid time format. Time has to be 0000 ~ 2359.");
        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidDateYearTest() {
        String s = "1/4/99 0900";
        DukeException expected = new DukeException("! Invalid year format. Year has to be yyyy.");
        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidDateTest() {
        String s = "1/99 0900";
        DukeException expected = new DukeException("! Invalid date format. Date time has to be dd/mm/yyyy.");
        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateValidTest() throws DukeException {
        String s = "1/4/1999";
        long expected = 922896000;
        long actual = DateProcessor.processDate(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateInvalidYearTest() {
        String s = "1/4/99";
        DukeException expected = new DukeException("! Invalid year format. Year has to be yyyy.");
        try {
            DateProcessor.processDate(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateInvalidDateTest() {
        String s = "1/4";
        DukeException expected = new DukeException("! Invalid date format. Date time has to be dd/mm/yyyy.");
        try {
            DateProcessor.processDate(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateInvalidTest() {
        String s = "-1/-1/0000";
        DukeException expected = new DukeException("! Invalid month format. Month has to be between 01 ~ 12.");
        try {
            System.out.println(DateProcessor.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDatePseudoValidDayTest() {
        String s = "32/12/0000";
        DukeException expected = new DukeException("✖ I could not recognise this date. "
                + "Unparseable date: \"32/12/0000 0000\"");
        try {
            System.out.println(DateProcessor.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDatePseudoValidMonthTest() {
        String s = "1/13/0000";
        DukeException expected = new DukeException("! Invalid month format. Month has to be between 01 ~ 12.");
        try {
            System.out.println(DateProcessor.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDatePseudoValidDayMonthTest() {
        String s = "32/13/0000";
        DukeException expected = new DukeException("! Invalid month format. Month has to be between 01 ~ 12.");
        try {
            System.out.println(DateProcessor.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }
}
