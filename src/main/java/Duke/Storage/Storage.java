package Duke.Storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.LocalDate;

import Duke.Exception.DukeException;
import Duke.TaskList.*;

import javax.lang.model.type.ArrayType;

public class Storage {
    private static File file;

    public Storage (String fileName) throws IOException {
        file = new File(fileName);
        if (file.createNewFile()){
            System.out.println("tasks.txt has been created");
        } else {
            System.out.println("tasks.txt has been found");
        }
    }

    public static void save(String file, Task task) throws IOException {
        FileWriter saveFile = new FileWriter(file);
        ArrayList<Task> taskList = task.getList();
        for(Task tasks:taskList) {
            saveFile.write(tasks.saveToFile());
        }
        saveFile.close();
    }

    public List<Task> load() throws DukeException, FileNotFoundException {
        boolean isDone;
        List<Task> taskList = new ArrayList<>();
        Scanner loaded = new Scanner(file);

        while(loaded.hasNext()) {
            String[] fileArr = loaded.nextLine().split(" \\| ", 5);
            String type = fileArr[0];
            String status = fileArr[1];
            //String taskPriority = fileArr[2];
            String description = fileArr[3];
            String deadline = fileArr[4];

            if(status.equals("1")) {
                isDone = true;
            } else {
                isDone = false;
            }

            switch (type) {
                case "T":
                    taskList.add(new ToDo(description, isDone));
                    break;
                case "D":
                    LocalDate dl = LocalDate.parse(fileArr[4]);
                    taskList.add(new Deadline(description, dl, isDone));
                    break;
                case "E":
                    String[] date = deadline.split(" ",2);
                    LocalDate eDate = LocalDate.parse(date[0]);
                    String[] startEndTime = deadline.split(" - ");
                    LocalTime start = LocalTime.parse(startEndTime[0]);
                    LocalTime end = LocalTime.parse(startEndTime[1]);
                    break;
                default:
                    throw new DukeException("Sorry. I do not understand.");
            }
        }
        return taskList;
    }

    public static File getFile(){
        return file;
    }
}
