package logic.file;

import common.exceptions.FileException;
import model.Chat;
import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static common.enums.CommandEnum.deadline;
import static logic.file.FileManager.FILE_PATH;
import static logic.file.FileManager.writeFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileManagerTest {
    @Test
    @DisplayName("write and load file")
    public void writeLoadFileTest() throws FileException {
        try {
            Path filePath = Paths.get(FILE_PATH);
            ArrayList<Task> taskList = new ArrayList<>();
            Chat chat = new Chat(deadline, "deadline homework /by 2022-11-14", taskList);
            int testFileSizeInBytes = 25;

            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);

            writeFile(chat);

            assertTrue(Files.exists(filePath));
            assertTrue(Files.isRegularFile(filePath));
            assertEquals(testFileSizeInBytes, Files.size(filePath));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
