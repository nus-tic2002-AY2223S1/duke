package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateProcessorTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void dateTimeToUnixInvalidTest(){
        DateProcessor d = new DateProcessor();
        String s = ".1/4/1999 0000";
        long expected = -1;
        long actual = d.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateTimeToUnixValidTest(){
        DateProcessor d = new DateProcessor();
        String s = "1/4/1999 0000";
        long expected = 922896000;
        long actual = d.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateToUnixInvalidTest(){
        DateProcessor d = new DateProcessor();
        String s = ".1/4/1999";
        long expected = -1;
        long actual = d.dateToUnix(s);
        assertEquals(expected, actual);
    }

    @Test
    public void dateToUnixValidTest(){
        DateProcessor d = new DateProcessor();
        String s = "1/4/1999";
        long expected = 922896000;
        long actual = d.dateTimeToUnix(s);
        assertEquals(expected, actual);
    }
}