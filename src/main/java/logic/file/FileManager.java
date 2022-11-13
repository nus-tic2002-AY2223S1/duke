package logic.file;

import common.exceptions.FileException;
import model.Chat;
import model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static logic.file.FileEncoder.encodedTask;

public class FileManager {
    private FileManager() {}

    public static final String FILE_PATH = "./data/duke.txt";

    /**
     * Return writes file content in duke.txt
     *
     * @param   chat
     * @throws  FileException
     */
    public static void writeFile(Chat chat) throws FileException {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : chat.getTaskList()) {
                writer.write(encodedTask(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new FileException();
        }
    }

    /**
     * Return creates and loads file in duke.txt
     *
     * @param   chat
     * @throws  FileException
     */
    public static void loadFile(Chat chat) throws FileException {
        try {
            Path filePath = Paths.get(FILE_PATH);
            assert (!Files.exists(filePath)) : "File is supposed to exist";

            // if file does not exist, create file
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }

            // write encoded content
            writeFile(chat);
        } catch (IOException e) {
            throw new FileException();
        }
    }
}
