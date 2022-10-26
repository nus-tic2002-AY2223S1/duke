package duke.util;

import duke.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Util class which used to process string instance.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class StringUtil {

    private StringUtil() {}

    /**
     * Check if the string instance is null or ''.
     *
     * @param str: Input string instance.
     * @return Boolean value if the string is empty.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Check if the string instance is null, '' or contain whitespaces only.
     *
     * @param str: Input string instance.
     * @return Boolean value if the string is blank.
     */
    public static boolean isBlank(String str) {
        boolean isEmpty = isEmpty(str);
        if (isEmpty) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove trailing and leading space of given string instance.
     *
     * @param str: Input string instance.
     * @return Trimmed string instance.
     */
    public static String trim(String str) {
        if (str == null) {
            return Constant.BLANK;
        }
        return StringUtils.trim(str);
    }

    /**
     * Join a list of object to a string by given delimiter.
     *
     * @param list: Target list instance.
     * @param delimiter: Delimiter used to join object.
     * @return Joined string instance.
     */
    public static String listToString(List<?> list, String delimiter) {
        if (CollectionUtil.isEmpty(list)) {
            return Constant.BLANK;
        }
        return list.stream().map(DataUtil::toString).collect(Collectors.joining(delimiter));
    }

    /**
     * Convert a string to a list of string by given delimiter.
     *
     * @param str: Target string instance.
     * @param delimiter: Delimiter appear in the string instance.
     * @return List of string separated by the delimiter.
     */
    public static List<String> stringToList(String str, String delimiter) {
        if (isBlank(str) || isBlank(delimiter)) {
            return Collections.emptyList();
        }
        return Arrays.asList(str.split(delimiter));
    }
}
