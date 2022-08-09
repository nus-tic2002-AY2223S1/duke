package util;

import constant.CommonConstant;

public class DataUtils {

    private DataUtils() {}

    public static String toString(Object object) {
        try {
            return String.valueOf(object);
        } catch (Exception e) {
            System.out.println("DataUtils invokes toString fails");
            return CommonConstant.BLANK;
        }
    }

    public static int toInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println("DataUtils invokes toInteger fails");
            return 0;
        }
    }

    public static long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            System.out.println("DataUtils invokes toLong fails");
            return 0L;
        }
    }
}
