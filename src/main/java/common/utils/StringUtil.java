package common.utils;

import common.enums.CommandEnum;

import static common.constants.CommonConstant.AT;
import static common.constants.CommonConstant.BY;
import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.SPACE;
import static common.constants.CommonConstant.ZERO_VAL;
import static common.constants.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.constants.CommonConstant.DELIMITER;

public class StringUtil {
    /**
     * getFirstWord returns the first word of the sentence
     *
     * @param {String} sentence
     * @return {String}
     */
    public static String getFirstWord(String sentence) {
        int index = sentence.contains(SPACE) ? sentence.indexOf(SPACE) : ZERO_VAL;
        String firstWord = sentence.substring(0, index).trim();

        try {
            if (index > 0) {
                return firstWord;
            }
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }

        return sentence;
    }

    /**
     * getDescriptionFromString returns the substring `description` in a string
     *
     * @param {String} sentence
     * @return {String}
     */
    public static String getDescriptionFromString(CommandEnum command, String sentence) {
        String commandString = command.toString();
        String substring = sentence;
        int index = sentence.indexOf(commandString);

        try {
            if (substring.contains(DELIMITER)) { // filter string with delimiter if it has
                substring = substring.substring(0, substring.lastIndexOf(DELIMITER) - 1);
            }
            if (index > -1 && commandString.length() != 0) { // filter string with command
                substring = substring.substring(index + INIT_INT_VAL + commandString.length());
            }
        } catch (Exception e) {}

        return substring;
    }

    /**
     * getTimeFromString returns the substring `time` in a string
     *
     * @param {String} sentence
     * @return {String}
     */
    public static String getTimeFromString(String sentence) {
        String separator = "";
        String substring = "";

        try {
            if (sentence.contains(AT)) {
                separator = DELIMITER + AT + SPACE;
            } else if (sentence.contains(BY)) {
                separator = DELIMITER + BY + SPACE;
            }
            int index = sentence.indexOf(separator);
            substring = sentence.substring(index + (separator).length());
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }

        return substring;
    }
}
