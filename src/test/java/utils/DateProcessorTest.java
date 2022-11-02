package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.utils.DateProcessor;
import duke.utils.DukeException;

public class DateProcessorTest {
    @Test
    public void dateTimeToUnixInvalidTest() throws DukeException {
        String s = ".1/4/1999 0000";
        DukeException expected = new DukeException("❌ Unparseable date: \".1/4/1999 0000 0000\"");

        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixValidTest() throws DukeException {
        String s = "1/11/2022 0000";
        long expected = 1667232000;
        long actual = DateProcessor.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateToUnixInvalidTest() {
        String s = ".1/4/1999";
        long expected = -1;
        long actual = DateProcessor.dateToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateToUnixValidTest() throws DukeException {
        String s = "1/11/2022";
        long expected = 1667232000;
        long actual = DateProcessor.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void unixToStringValidTest() {
        long l = 1667260800;
        String expected = "Tue 01 Nov 2022, 08:00";
        String actual = DateProcessor.unixToString(l);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeNoTimeTest() throws DukeException {
        String s = "1/4/1999";
        DukeException expected = new DukeException("⚠️ Invalid date/time format. Date time has to be dd/mm/yyyy HHmm.");

        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidTimeTest() {
        String s = "1/4/1999 900";
        DukeException expected = new DukeException("⚠️ Invalid time format. Time has to be 0000 ~ 2359.");

        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidDateYearTest() {
        String s = "1/4/99 0900";
        DukeException expected = new DukeException("⚠️ Invalid year format. Year has to be yyyy.");

        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidDateTest() {
        String s = "1/99 0900";
        DukeException expected = new DukeException("⚠️ Invalid date format. Date time has to be dd/mm/yyyy.");
        try {
            DateProcessor.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeValidTest() throws DukeException {
        String s = "1/4/1999 0000";
        long expected = 922896000;
        long actual = DateProcessor.processDateTime(s);
        assertEquals(expected, actual);
    }
}
