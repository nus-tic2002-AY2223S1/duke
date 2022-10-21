package duke.util;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

class ExceptionUtilTest {

	@Test
	void testGetStackTraceAsString() {
		String message = ExceptionUtil.getStackTraceAsString(new DukeException("custom message"));
		boolean flag = message.contains("custom message");
		Assertions.assertTrue(flag);
	}
}