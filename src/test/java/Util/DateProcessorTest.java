package Util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateProcessorTest {
    @Test
    public void dateTimeToUnixInvalidTest(){
        String s = ".1/4/1999 0000";
        long expected = -1;
        long actual = DateProcessor.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateTimeToUnixValidTest(){
        String s = "1/11/2022 0000";
        long expected = 1667232000;
        long actual = DateProcessor.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateToUnixInvalidTest(){
        String s = ".1/4/1999";
        long expected = -1;
        long actual = DateProcessor.dateToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateToUnixValidTest(){
        String s = "1/11/2022";
        long expected = 1667232000;
        long actual = DateProcessor.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void unixToStringValidTest(){
        long l = 1667260800;
        String expected = "Tue 01 Nov 2022, 08:00";
        String actual = DateProcessor.unixToString(l);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeNoTimeTest(){
        String s = "1/4/1999";
        long expected = -1;
        long actual = DateProcessor.processDateTime(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeInvalidTimeTest(){
        String s = "1/4/1999 900";
        long expected = -1;
        long actual = DateProcessor.processDateTime(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeInvalidDateYYYYTest(){
        String s = "1/4/99 0900";
        long expected = -1;
        long actual = DateProcessor.processDateTime(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeInvalidDateDDMMTest(){
        String s = "1/99 0900";
        long expected = -1;
        long actual = DateProcessor.processDateTime(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeValidTest(){
        String s = "1/4/1999 0000";
        long expected = 922896000;
        long actual = DateProcessor.processDateTime(s);
        assertEquals(expected, actual);
    }
}