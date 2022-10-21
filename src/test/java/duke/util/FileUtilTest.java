package duke.util;

import duke.constant.Constant;
import org.apache.commons.io.FileSystem;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

class FileUtilTest {

    private static final String dir = String.format("%s/%s",
            FileSystems.getDefault().getPath("").toAbsolutePath(),
            "src/test/java/duke/util");

    @Test
    void testReadFileContent() {
        String path = String.format("%s/test-read.txt", dir);
        String content = FileUtil.readFileContent(new File(path));
        Assertions.assertEquals("abcdefg\n1234567", content);
    }

    @Test
    void testWriteStringToFile() throws IOException {
        String path = String.format("%s/test-write.txt", dir);
        File file = new File(path);
        FileUtil.writeStringToFile(file, "data");
        boolean exists = file.exists();
        Assertions.assertTrue(exists);
        // cleanup
        FileUtils.delete(file);
    }

    @Test
    void testDeleteFile() throws IOException {
        String path = String.format("%s/test-delete.txt", dir);
        File file = new File(path);
        FileUtil.writeStringToFile(new File(path), "");
        FileUtils.delete(file);
        Assertions.assertFalse(file.exists());
    }
}
