package duke;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class for LocalDateTime, used for storing and formatting of dates and times
 * @see LocalDateTime
 */
public class DateTime implements Serializable {
    public static final long serialVersionUID = 1L;

    // Default datetime is "d-M-y H:m", e.g. 01-05-2021 12:30
    // Store as a static member to ensure consistent representation of all objects
    protected static String datePattern = "yyyy-MM-dd";
    protected static String timePattern = "HH:mm";

    protected LocalDateTime dateTime;
    /**
     * Construct a dateTime object with a string. The string must be following the
     * format given in datePattern and timePattern attribute of this class.
     * @param dateTimeString A string contain the date (and time) to be parsed
     */
    public DateTime(String dateTimeString) {
        try {
            dateTime =
                    LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(datePattern + " " + timePattern));
        } catch (DateTimeParseException e) {
            // Fail to parse a datetime - try to parse a date (without time)
            LocalDate date = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern(datePattern));
            dateTime = LocalDateTime.of(date, LocalTime.parse("00:00"));
        }
    }
    /**
     * Check whether this dateTime instance falls on a same date as the other dateTime instance
     * @param dateTime DateTime instance used for comparison
     * @return True for same date
     */
    public Boolean isSameDate(DateTime dateTime) {
        return this.dateTime.toLocalDate().isEqual(dateTime.dateTime.toLocalDate());
    }
    /**
     * Format this dateTime instance to a string, using format defined
     */
    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"+ " " + timePattern));
    }
}
