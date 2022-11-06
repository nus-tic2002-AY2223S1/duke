package utils;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.impl.Ui;
import duke.utils.DateProcessor;
import duke.utils.DukeException;

public class DateProcessorCnTest {
    private static DateProcessor d = new DateProcessor(Ui.LocaleRegion.CN);

    @Test
    public void dateTimeToUnixInvalidTest() {
        String s = ".1/4/1999 0000";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \".1/4/1999 0000\"");

        try {
            d.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixPseudoValidDayTest() {
        String s = "32/12/1999 0000";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \"32/12/1999 0000\"");

        try {
            d.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixPseudoValidMonthTest() {
        String s = "30/13/1999 0000";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \"30/13/1999 0000\"");

        try {
            d.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixPseudoValidDayMonthTest() {
        String s = "31/02/1999 0000";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \"31/02/1999 0000\"");

        try {
            d.dateTimeToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateTimeToUnixNoTimeTest() throws DukeException {
        String s = "1/11/2022";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \"1/11/2022\"");
        try {
            d.dateTimeToUnix(s);
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
            actual = d.dateToUnix(s);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void dateToUnixInvalidTest() {
        String s = ".1/4/1999";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \".1/4/1999 0000\"");
        try {
            d.dateToUnix(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void dateToUnixPseudoValidDayTest() {
        String s = "32/4/1999";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \"32/4/1999 0000\"");
        try {
            d.dateToUnix(s);
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
            actual = d.dateToUnix(s);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void unixToStringValidTest() {
        long l = 1667260800;
        String expected = "22年11月1日, 上午8:00";
        String actual = d.unixToString(l);
        assertEquals(expected, actual);
    }

    @Test
    public void unixToSimplifiedStringTest() {
        long l = 1667260800;
        String expected = "上午08:00";
        String actual = d.unixToSimplifiedString(l);
        assertEquals(expected, actual);
    }

    @Test
    public void getMetaTimeStampTest() {
        String expected = d.unixToSimplifiedString(Instant.now().getEpochSecond());
        String actual = d.getMetaTimeStamp();
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeValidTest() throws DukeException {
        String s = "1/4/1999 0000";
        long expected = 922896000;
        long actual = d.processDateTime(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateTimeNoTimeTest() {
        String s = "1/4/1999";
        DukeException expected = new DukeException("! 日期/时间格式无效。 日期时间格式必须是 dd/mm/yyyy HHmm。");
        try {
            d.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidTimeTest() {
        String s = "1/4/1999 900";
        DukeException expected = new DukeException("! 时间格式无效。 时间格式必须是 0000 ~ 2359。");
        try {
            d.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidDateYearTest() {
        String s = "1/4/99 0900";
        DukeException expected = new DukeException("! 年份格式无效。年份必须是 yyyy。");
        try {
            d.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimeInvalidDateTest() {
        String s = "1/99 0900";
        DukeException expected = new DukeException("! 日期格式无效。日期必须是 dd/mm/yyyy。");
        try {
            d.processDateTime(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateValidTest() throws DukeException {
        String s = "1/4/1999";
        long expected = 922896000;
        long actual = d.processDate(s);
        assertEquals(expected, actual);
    }

    @Test
    public void processDateInvalidYearTest() {
        String s = "1/4/99";
        DukeException expected = new DukeException("! 年份格式无效。年份必须是 yyyy。");
        try {
            d.processDate(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateInvalidDateTest() {
        String s = "1/4";
        DukeException expected = new DukeException("! 日期格式无效。日期必须是 dd/mm/yyyy。");
        try {
            d.processDate(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateInvalidTest() {
        String s = "-1/-1/0000";
        DukeException expected = new DukeException("! 月份格式无效。 月份必须介于 1 ~ 12。");
        try {
            System.out.println(d.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDatePseudoValidDayTest() {
        String s = "32/12/0000";
        DukeException expected = new DukeException("✖ 我不理解这个日期。 "
                + "Unparseable date: \"32/12/0000 0000\"");
        try {
            System.out.println(d.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDatePseudoValidMonthTest() {
        String s = "1/13/0000";
        DukeException expected = new DukeException("! 月份格式无效。 月份必须介于 1 ~ 12。");
        try {
            System.out.println(d.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDatePseudoValidDayMonthTest() {
        String s = "32/13/0000";
        DukeException expected = new DukeException("! 月份格式无效。 月份必须介于 1 ~ 12。");
        try {
            System.out.println(d.processDate(s));
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeNoRangeTest() {
        String s = "2/4/1999";
        long expected1 = 922982400;
        long expected2 = 923068799;
        try {
            long[] actual = d.processDateTimeRange(s);
            assertEquals(expected1, actual[0]);
            assertEquals(expected2, actual[1]);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeNoEndDateTest() {
        String s = "2/4/1999-";
        DukeException expected = new DukeException("! 日期格式无效。日期必须是 dd/mm/yyyy。");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeNoStartDateTest() {
        String s = "-2/4/1999";
        DukeException expected = new DukeException("! 日期格式无效。日期必须是 dd/mm/yyyy。");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeNoSeparatorTest() {
        String s = "2/4/1999 3/4/1999";
        DukeException expected = new DukeException("! 日期区间必须使用 '-' 区隔");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeNoSeparatorNoSpaceTest() {
        String s = "2/4/19993/4/1999";
        DukeException expected = new DukeException("! 日期区间必须使用 '-' 区隔");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeDayToDayValidTest() {
        String s = "1/4/1999 - 2/4/1999";
        long expected1 = 922896000;
        long expected2 = 923068799;
        try {
            long[] actual = d.processDateTimeRange(s);
            assertEquals(expected1, actual[0]);
            assertEquals(expected2, actual[1]);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeDayToDayInvalidEndBeforeStartTest() {
        String s = "2/4/1999 - 1/4/1999";
        DukeException expected = new DukeException("! 结束早于开始。穿越时空不被允许。");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeDayToDayIdenticalTest() {
        String s = "1/4/1999 - 1/4/1999";
        long expected1 = 922896000;
        try {
            long[] actual = d.processDateTimeRange(s);
            assertEquals(expected1, actual[0]);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeTimeToTimeInvalidEndBeforeStartTest() {
        String s = "1/4/1999 2359 - 1/4/1999 0000";
        DukeException expected = new DukeException("! 结束早于开始。穿越时空不被允许。");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeTimeToTimeIdenticalTest() {
        String s = "2/4/1999 0000 - 2/4/1999 0000";
        DukeException expected = new DukeException("! 开始和结束时间相同。");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeDateTimeToDateTest() {
        String s = "1/4/1999 2359 - 2/4/1999";
        DukeException expected = new DukeException("! 区间格式无效。 开始和结束格式必须一致。区间格式必须是"
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm。");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }

    @Test
    public void processDateTimRangeDateToDateTimeTest() {
        String s = "1/4/1999 - 2/4/1999 0000";
        DukeException expected = new DukeException("! 区间格式无效。 开始和结束格式必须一致。区间格式必须是"
                + "\n\tdd/mm/yyyy - dd/mm/yyyy \n\tdd/mm/yyyy HHmm - dd/mm/yyyy HHmm。");
        try {
            d.processDateTimeRange(s);
        } catch (DukeException e) {
            assertEquals(expected.getMessage(), e.getMessage());
        }
    }
}
