package parser;

import exception.IllegalContentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Parser {

    public static LocalDateTime parseStringToDateTime(String input) throws IllegalContentException {
        input = input.trim();

        if (Pattern.matches("[0-9]{1,2}([/\\-])[0-9]{1,2}([/\\-])[0-9]{4} [0-9]{4}", input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

            return LocalDateTime.parse(input, formatter);
        } else {
            throw new IllegalContentException("☹ OOPS!!! The input date format is incorrect.");
        }
    }

    public static String parseDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

}
