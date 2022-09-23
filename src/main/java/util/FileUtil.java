package util;

import constant.Constant;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    private FileUtil() {}

    public static String readFileContent(File file) {
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ExceptionUtil.getStackTraceAsString(e));
        }
        return Constant.BLANK;
    }

    public static void writeStringToFile(File file, String data) {
        try {
            FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ExceptionUtil.getStackTraceAsString(e));
        }
    }

    public static void deleteFile(File file) {
        try {
            FileUtils.delete(file);
        } catch (IOException e) {
            System.out.println(ExceptionUtil.getStackTraceAsString(e));
        }
    }
}
