package catbot.utils;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UtilsTest {
    @Test
    public void testStringToDateConversion(){
        assertEquals(LocalDate.of(2020,1,1), Utils.convertDateStringWithSlashToLocalDate("1/1/2020"));
    }

    @Test
    public void testTwoDigitDateStringToDateConversion(){
        assertEquals(LocalDate.of(2020,12,1), Utils.convertDateStringWithSlashToLocalDate("01/12/2020"));
    }

    @Test
    public void testTwoDigitDateStringToDateConversion_invalidDate(){
        try {
            assertEquals(LocalDate.of(2020, 12, 1), Utils.convertDateStringWithSlashToLocalDate("01/13/2020"));
            fail();
        } catch (Exception e){
            assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): 13", e.getMessage());
        }
    }

    @Test
    public void testStringToTimeConversion(){
        assertEquals(LocalTime.of(0,1), Utils.convertFourDigitTimeStringToLocalTime("0001"));
        assertEquals(LocalTime.of(23,58), Utils.convertFourDigitTimeStringToLocalTime("2358"));
    }

    @Test
    public void testSha1Hash() throws NoSuchAlgorithmException {
        assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d",Utils.hashString("abc"));
        assertEquals("03de6c570bfe24bfc328ccd7ca46b76eadaf4334",Utils.hashString("abcde"));
    }
}
