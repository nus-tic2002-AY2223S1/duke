package entities;
import command.*;
import exception.DukeException;
import org.apache.commons.io.FileUtils;
import utils.DukeUtils;

import java.io.*;
import java.util.List;

public class Storage {
    private static File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public static File load() throws DukeException, FileNotFoundException {
        return file;
    }

    public static void save(List<Task> taskList) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (Task task : taskList) {
                stringBuffer.append(task.toCommandString() + "\n");
            }
            FileUtils.write(file, stringBuffer.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
