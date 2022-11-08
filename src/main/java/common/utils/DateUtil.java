package common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    /**
     * Return date in MMM d yyyy
     *
     * @param   inputDateTime   date and time inputted by user
     */
    public static String dateTimeConverter(String inputDateTime) {
        LocalDateTime d = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }
}
