package common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    /**
     * Return date in MMM d yyyy
     *
     * @param   inputDate   date inputted by user
     */
    public static String dateConverter(String inputDate) {
        LocalDate date = LocalDate.parse(inputDate);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
