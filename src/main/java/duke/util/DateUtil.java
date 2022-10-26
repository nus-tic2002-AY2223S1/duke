package duke.util;

import duke.exception.CommandArgsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Util class used to handle date.
 *
 * @author Dex
 * @date 2022/10/25
 */
public class DateUtil {

    private DateUtil() {}

    /**
     * Return localDateTime instance based on given input and pattern.
     *
     * @param input: Date in text.
     * @param pattern: Date pattern.
     * @return Parse LocalDateTime Instance.
     */
    public static LocalDateTime parse(String input, String pattern) {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException | NullPointerException exception) {
            throw new CommandArgsException("time format invalid, it should be in (yyyy-MM-dd HH:mm) format");
        }
    }
}
