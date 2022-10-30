package common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    /**
     * dateConverter return date in MMM d yyyy
     *
     * @return {String}
     */
    public static String dateConverter(String inputDate) {
        LocalDate date = LocalDate.parse(inputDate);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
