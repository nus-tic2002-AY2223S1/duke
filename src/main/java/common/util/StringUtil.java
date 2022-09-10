package common.util;

import static common.constant.CommonConstant.INIT_INT_VAL;
import static common.constant.CommonConstant.ZERO_VAL;
import static common.constant.CommonConstant.SPACE;

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

        if (index > 0) {
            return firstWord;
        }
        return sentence;
    }

    /**
     * getSubstringFromSentence returns the substring after command in the sentence
     *
     * @param {String} command
     * @param {String} sentence
     * @return {String}
     */
    public static String getSubstringFromSentence(String command, String sentence) {
        int index = sentence.indexOf(command);
        String substring = sentence.substring(index + INIT_INT_VAL + command.length());

        if (index > -1) {
            return substring;
        }
        return sentence;
    }
}
