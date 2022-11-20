import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type Date formatter.
 */
public class DateFormatter {

    /**
     * Gets formatted date.
     *
     * @param date the date
     * @return the formatted date
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static String getFormattedDate(LocalDate date) throws IllegalArgumentException {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return formatter1.format(date);
    }


    /**
     * Gets date from string.
     *
     * @param date the date
     * @return the date from string
     * @throws DateTimeParseException the date time parse exception
     */
    public static LocalDate getDateFromString(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
