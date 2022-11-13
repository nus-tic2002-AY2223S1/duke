package duke.util;

import duke.constant.Constant;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Util class used to handle file operation.
 *
 * @author Dex
 * @date 2022/10/25
 */
public class FileUtil {

    private FileUtil() {}

    /**
     * Reads the content from given file instance.
     *
     * @param file: File instance.
     * @return Content from file.
     */
    public static String readFileContent(File file) {
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ExceptionUtil.getStackTraceAsString(e));
        }
        return Constant.BLANK;
    }

    /**
     * Writes data to the given file instance.
     *
     * @param file: File instance.
     * @param data: Persistent data.
     */
    public static void writeStringToFile(File file, String data) {
        try {
            FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ExceptionUtil.getStackTraceAsString(e));
        }
    }

    /**
     * Delete file from disk.
     *
     * @param file: Target file instance.
     */
    public static void deleteFile(File file) {
        try {
            FileUtils.delete(file);
        } catch (IOException e) {
            System.out.println(ExceptionUtil.getStackTraceAsString(e));
        }
    }
}
