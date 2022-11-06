package logic.file;

import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static logic.file.FileEncoder.encodedTask;
import static logic.file.FileManager.FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FileEncoderTest {
    @Test
    @DisplayName("encoded task")
    public void encodedTaskTest() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();

            Path filePath = Paths.get(FILE_PATH);
            Task task = new Task("homework");

            encodedTask(task);

            BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"));
            assertNotEquals("D|0|homework|Nov 14 2022", reader.readLine());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
