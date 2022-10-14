package duke.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

    private ExceptionUtil() {}

	/**
	 * @description convert stack trace as string message
	 * @author Dex
	 * @date 2022/09/06
	 * @param ex: throwable instance
	 */
	public static String getStackTraceAsString(Throwable ex) {
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
}