package application.helpers;

import domain.exceptions.DukeArgumentException;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonHelper {
    /**
     * Converts string input into Name and Remark and returns an array with 2 string objects.
     * If keyword exists in input, the index + 2 to skip.
     *
     * @param input String.
     * @param keyword String.
     * @return String[] formatted.
     */
    public static String[] formatPassedName(String input, String keyword){
        String[] formatted = new String[2];
        Integer idx = input.indexOf("/");
        if(idx > 0 && idx < input.length()) {
            formatted[0] = input.substring(0, idx).trim();
            if(input.contains(keyword)){
                Integer keywordIdx = input.indexOf(keyword);
                formatted[1] = input.substring(keywordIdx+2).trim();
            } else {
                formatted[1] = input.substring(idx+1).trim();
            }
        }
        else if(idx == 0) {
            formatted[1] = input.replace("/", "").trim();
        } else {
            formatted[0] = input.replace("/", "").trim();
        }
        return formatted;
    }

    /**
     * Prints message as a new line.
     *
     * @param message String.
     */
    public static void printMessage(String message){
        System.out.println(message);
    }

    /**
     * Checks if string is empty or whitespace or null.
     *
     * @param text String.
     * @return boolean.
     */
    public static boolean isEmptyOrNull(String text) {
        return text != null ? text.isEmpty() || text.isBlank() : true;
    }

    /**
     * Converts string to an integer.
     *
     * @param text String.
     * @return Integer.
     * @throws DukeValidationException if text is empty.
     */
    public static int getNumber(String text) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(text)) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Id"));
        }
        return Integer.parseInt(text);
    }

    /**
     * Converts boolean to an integer.
     *
     * @param bool boolean.
     * @return Integer.
     */
    public static int booleanToInteger(boolean bool) {
        return Boolean.compare(bool, false);
    }

    /**
     * Converts string to a datetime.
     *
     * @param date String.
     * @return LocalDateTime.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public static LocalDateTime convertStringToDateTime(String date) throws DukeArgumentException {
        String formats = "[yyyy-MM-dd HH:mm][yyyy-MM-dd h:mm a][MMM dd yyyy HH:mm][MMM dd yyyy h:mm a]";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formats);
            return LocalDateTime.parse(date, formatter);
        } catch (Exception ex){
            throw new DukeArgumentException(String.format(MessageConstants.TASK_VALIDATION_FORMAT_ERROR, formats));
        }
    }

    /**
     * Converts string to a datetime where time is 00:00.
     *
     * @param date String.
     * @return LocalDateTime.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public static LocalDateTime convertStringToDate(String date) throws DukeArgumentException {
        String formats = "[yyyy-MM-dd][MMM dd yyyy]";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formats);
            return LocalDate.parse(date, formatter).atStartOfDay();
        } catch (Exception ex){
            throw new DukeArgumentException(String.format(MessageConstants.TASK_VALIDATION_FORMAT_ERROR, formats));
        }
    }
}
