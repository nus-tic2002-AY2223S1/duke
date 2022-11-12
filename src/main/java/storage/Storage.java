package storage;

import tasklist.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    //    private static TaskList taskList;
    public static String path = homeDirectory() + "/duke/data/duke.txt";

    public static File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public static File load() throws FileNotFoundException {
        return file;
    }

    public static String homeDirectory() {
        String homeDirectory = System.getProperty("user.home");
        return homeDirectory;
    }

    public static void fileExist() {
        if (!file.exists()) {
            File f = new File(path);
            if (!f.exists())
                f.getParentFile().mkdirs();
        }
    }

    public static void save(ArrayList<Task> taskList) {
        try {
            fileExist();
            assert path != null : "File path cannot be null";
            FileOutputStream writer = new FileOutputStream(path);

            for (Task task : taskList) {
                writer.write((task.toOutput() + '\n').getBytes());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}