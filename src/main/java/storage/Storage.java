package storage;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Return the file, tasks.txt
     *
     * @return tasks file
     * @throws FileNotFoundException If file is not found
     */
    public static File load() throws FileNotFoundException {
        return file;
    }

    /**
     * Save the content of task list into the file, tasks.txt.
     * If file/directory cannot be found, create one.
     *
     * @param filePath Path of the file, tasks.txt
     * @param taskList Task list containing all task
     */
    public static void save(String filePath, ArrayList<Task> taskList) {
        try {
            if (!file.exists()) {
                File f = new File(filePath);
                if (!f.exists()) {
                    f.getParentFile().mkdirs();
                }
            }
            FileOutputStream writer = new FileOutputStream(filePath);
            for (Task task : taskList) {
                writer.write((task.stringToOutput() + '\n').getBytes());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
