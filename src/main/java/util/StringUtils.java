package util;

import constant.CommonConstant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    private StringUtils() {}

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
            return CommonConstant.BLANK;
        }
        return str.trim();
    }

    public String listToString(List<?> list, String delimiter) {
        if (CollectionUtils.isEmpty(list)) {
            return CommonConstant.BLANK;
        }
        return list.stream().map(DataUtils::toString).collect(Collectors.joining(delimiter));
    }

    public List<String> StringToList(String str, String delimiter) {
        if (isBlank(str)) {
            return Collections.emptyList();
        }
        return Arrays.asList(str.split(delimiter));
    }

    public static void main(String[] args) {
        boolean blank1 = StringUtils.isBlank("      ");
        boolean blank2 = StringUtils.isBlank(null);
        boolean blank3 = StringUtils.isBlank("");
        boolean blank4 = StringUtils.isBlank(" aa ");
        System.out.println(blank1);
        System.out.println(blank2);
        System.out.println(blank3);
        System.out.println(blank4);
    }
}
