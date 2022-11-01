package application.helpers;

import domain.exceptions.DukeValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonHelper {

    /**
     * Split input into Name and Remark and returns an array with 2 string objects
     */
    public static String[] formatPassedName(String n, String keyword){
        String[] formatted = new String[2];
        Integer idx = n.indexOf("/");
        if(idx > 0 && idx < n.length()) {
            formatted[0] = n.substring(0, idx).trim();
            if(n.contains(keyword)){
                Integer keywordIdx = n.indexOf(keyword);
                formatted[1] = n.substring(keywordIdx+2).trim();
            } else {
                formatted[1] = n.substring(idx+1).trim();
            }
        }
        else
            formatted[0] = n.replace("/", "").trim();
        return formatted;
    }

    /**
     * Print line with a message
     */
    public static void printMessage(String message){
        System.out.println(message);
    }

    /**
     * Check if string is empty or whitespace or null
     * Return boolean result
     */
    public static boolean isEmptyOrNull(String text) {
        return text != null ? text.isEmpty() || text.isBlank() : true;
    }

    /**
     * Convert string to an integer
     * Returns integer result
     */
    public static int getNumber(String text) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(text))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Id"));
        return Integer.parseInt(text);
    }

    /**
     * Convert boolean to an integer
     * Returns integer result
     */
    public static int boolToInt(boolean b) {
        return Boolean.compare(b, false);
    }

    /**
     * Convert string to a datetime
     * Returns datetime result
     */
    public static LocalDateTime convertStringToDateTime(String date) throws DukeValidationException {
        String formats = "[yyyy-MM-dd HH:mm][yyyy-MM-dd h:mm a][MMM dd yyyy HH:mm][MMM dd yyyy h:mm a]";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formats);
            return LocalDateTime.parse(date, formatter);
        } catch (Exception ex){
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_FORMAT_ERROR, formats));
        }
    }

    /**
     * Convert string to a datetime
     * Returns datetime result with a date value and 00:00 as the hours and minutes
     */
    public static LocalDateTime convertStringToDate(String date) throws DukeValidationException {
        String formats = "[yyyy-MM-dd][MMM dd yyyy]";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formats);
            return LocalDate.parse(date, formatter).atStartOfDay();
        } catch (Exception ex){
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_FORMAT_ERROR, formats));
        }
    }
}
