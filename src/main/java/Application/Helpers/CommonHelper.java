package Application.Helpers;

import Domain.Exceptions.DukeValidationException;

import java.util.Arrays;

public class CommonHelper {
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

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static boolean isEmptyOrNull(String text) {
        return text != null ? text.isEmpty() || text.isBlank() : true;
    }

    public static int getNumber(String text) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(text))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Id"));
        return Integer.parseInt(text);
    }

    public static int boolToInt(boolean b) {
        return Boolean.compare(b, false);
    }
}
