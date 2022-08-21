package util;

import constant.CommonConstant;
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
            return CommonConstant.BLANK;
        }
        return StringUtils.trim(str);
    }

    public static String listToString(List<?> list, String delimiter) {
        if (CollectionUtil.isEmpty(list)) {
            return CommonConstant.BLANK;
        }
        return list.stream().map(DataUtil::toString).collect(Collectors.joining(delimiter));
    }

    public static List<String> stringToList(String str, String delimiter) {
        if (isBlank(str)) {
            return Collections.emptyList();
        }
        return Arrays.asList(str.split(delimiter));
    }

    public static void main(String[] args) {
        boolean blank1 = StringUtil.isBlank("      ");
        boolean blank2 = StringUtil.isBlank(null);
        boolean blank3 = StringUtil.isBlank("");
        boolean blank4 = StringUtil.isBlank(" aa ");
        System.out.println(blank1);
        System.out.println(blank2);
        System.out.println(blank3);
        System.out.println(blank4);

        boolean empty1 = StringUtil.isEmpty("");
        boolean empty2 = StringUtil.isEmpty(null);
        boolean empty3 = StringUtil.isEmpty("   ");
        boolean empty4 = StringUtil.isEmpty("   aa   ");
        System.out.println("empty1 = " + empty1);
        System.out.println("empty2 = " + empty2);
        System.out.println("empty3 = " + empty3);
        System.out.println("empty4 = " + empty4);

        List<String> strings = StringUtil.stringToList("1,2,3,4,5,10,15", ",");
        System.out.println(strings);
    }
}
