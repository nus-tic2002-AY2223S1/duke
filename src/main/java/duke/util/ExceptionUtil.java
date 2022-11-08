package duke.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Util class used to handle exception.
 *
 * @author Dex
 * @date 2022/10/25
 */
public class ExceptionUtil {

    private ExceptionUtil() {
    }

    /**
     * Returns stack trace as string message.
     *
     * @param ex: throwable instance.
     * @return Formatted stack trace.
     */
    public static String getStackTraceAsString(Throwable ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}