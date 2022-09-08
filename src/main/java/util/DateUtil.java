package util;

import constant.Constant;
import exception.CommandArgsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateUtil {

    private DateUtil() {}

    public static LocalDateTime parse(String input, String pattern) {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException exception) {
            throw new CommandArgsException("time format invalid, it should be in (yyyy-MM-dd HH:mm) format");
        }
    }
}
