package duke.util;

import duke.constant.Constant;
import duke.exception.CommandArgsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class DateUtilTest {

    @Test
    void testParse() {
        LocalDateTime parse1 = DateUtil.parse("2000-01-01 00:00", Constant.Time.INPUT_FORMAT);
        Assertions.assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0), parse1);

        LocalDateTime parse2 = DateUtil.parse("01 Jan 2000, Sat 00:00", Constant.Time.DISPLAY_FORMAT);
        Assertions.assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0), parse2);
    }

    @Test
    void testParseWithException() {
        try {
            LocalDateTime parse1 = DateUtil.parse(null, Constant.Time.INPUT_FORMAT);
        } catch (CommandArgsException e) {
            Assertions.assertEquals("error code: 601, message: time format invalid, it should be in (yyyy-MM-dd HH:mm) format", e.getMessage());
        }

        try {
            LocalDateTime parse2 = DateUtil.parse("2000/01/01 00:00", Constant.Time.INPUT_FORMAT);
        } catch (Exception e) {
            Assertions.assertEquals("error code: 601, message: time format invalid, it should be in (yyyy-MM-dd HH:mm) format", e.getMessage());
        }
    }
}
