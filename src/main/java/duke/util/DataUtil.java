package duke.util;

import duke.constant.Constant;

/**
 * Util class which used to handle the conversion of data.
 *
 * @author Dex
 * @date 2022/10/25
 */
public class DataUtil {

    private DataUtil() {}

    /**
     * Converts an object instance to string.
     *
     * @param object: Target object instance.
     * @return String instance of object.
     */
    public static String toString(Object object) {
        try {
            return String.valueOf(object);
        } catch (Exception e) {
            System.out.println("DataUtils invokes toString fails");
            return Constant.BLANK;
        }
    }

    /**
     * Converts a string instance to integer.
     *
     * @param str: Target string instance.
     * @return Integer instance.
     */
    public static int toInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println("DataUtils invokes toInteger fails");
            return 0;
        }
    }

    /**
     * Converts a string instance to long.
     *
     * @param str: Target string instance.
     * @return Long instance.
     */
    public static long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            System.out.println("DataUtils invokes toLong fails");
            return 0L;
        }
    }
}
