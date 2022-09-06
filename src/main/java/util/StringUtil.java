package util;

import constant.Constant;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    private StringUtil() {}

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

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

    public static String trim(String str) {
        if (str == null) {
            return Constant.BLANK;
        }
        return StringUtils.trim(str);
    }

    public static String capitalize(String str) {
        if (str == null) {
            return Constant.BLANK;
        }
        return StringUtils.capitalize(str);
    }

    public static String listToString(List<?> list, String delimiter) {
        if (CollectionUtil.isEmpty(list)) {
            return Constant.BLANK;
        }
        return list.stream().map(DataUtil::toString).collect(Collectors.joining(delimiter));
    }

    public static List<String> stringToList(String str, String delimiter) {
        if (isBlank(str)) {
            return Collections.emptyList();
        }
        return Arrays.asList(str.split(delimiter));
    }
}
