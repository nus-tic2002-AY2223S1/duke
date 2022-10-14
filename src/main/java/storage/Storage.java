package storage;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.DukeException;

public class Storage {
    private static File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public static File load() throws DukeException, FileNotFoundException {
        return file;
    }

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
