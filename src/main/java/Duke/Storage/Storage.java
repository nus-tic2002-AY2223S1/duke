package Duke.Storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import Duke.TaskList.*;

import javax.lang.model.type.ArrayType;

public class Storage {
    private static File file;

    public Storage (String fileName) throws IOException {
        file = new File(fileName);
        if (file.createNewFile()){
            System.out.println("tasks.txt has been created");
        } else {
            System.out.println("task.txt has been found");
        }
    }

    public static void save(String file, Task task) throws IOException {
        FileWriter saveFile = new FileWriter(file);
        ArrayList<Task> taskList = Task.getList();
        for(Task tasks:taskList) {
            saveFile.write(tasks.saveToFile());
        }
    }

    public static File getFile(){
        return file;
    }
}
