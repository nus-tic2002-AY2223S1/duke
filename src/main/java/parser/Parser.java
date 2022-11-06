package parser;

import exception.IllegalContentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Parser {
    
    /**
     * Parse string to a LocalDateTime object
     *
     * @param input datetime string
     * @return a LocalDateTime object
     * @throws IllegalContentException
     */
    public static LocalDateTime parseStringToDateTime(String input) throws IllegalContentException {
        input = input.trim();
        //12-12-2022 2222
        if (Pattern.matches("[0-9]{1,2}(/)[0-9]{1,2}(/)[0-9]{4} [0-9]{4}", input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
            
        } else if (Pattern.matches("[0-9]{1,2}(-)[0-9]{1,2}(-)[0-9]{4} [0-9]{4}", input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
            
        } else {
            throw new IllegalContentException("☹ OOPS!!! The input date time format is incorrect.");
        }
    }
    
    /**
     * Parse a LocalDateTime object to a string
     * @param dateTime a LocalDateTime object
     * @return datetime string
     */
    public static String parseDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

}
