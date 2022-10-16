package Domain.Repositories;

import Application.Helpers.MessageConstants;
import Domain.Aggregates.Tracker.*;
import Domain.Exceptions.DukeFileException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageRepository implements IStorageRepository{
    private static final String FILE_PATH = "data/duke.txt";
    private static final String HEADER = "ID | Type | Is Done | Name | Remarks\n";

    public StorageRepository(){

    }

    public File init(){
        File file = new File(FILE_PATH);
        file.setWritable(true);
        file.setReadable(true);
        if(!file.exists()) {
            try {
                file.createNewFile();
                write(file, HEADER);
            } catch (IOException ex){
                new DukeFileException(MessageConstants.TASK_GET_ERROR);
            }
        }
        return file;
    }

    public void write(File file, String row) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(row + "\n");
            writer.close();
        } catch (IOException ex){
            new DukeFileException(MessageConstants.TASK_SAVE_ERROR);
        }
    }

    public void override(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.append(HEADER);
            for (Task task : tasks) {
                writer.append(task.toString() + "\n");
            }
            writer.flush();
        } catch (IOException ex){
            new DukeFileException(MessageConstants.TASK_SAVE_ERROR);
        }
    }

    public ArrayList<Task> convertToTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scnr = new Scanner(new FileReader(FILE_PATH));
            String[] str;
            scnr.nextLine();
            while (scnr.hasNext()) {
                str = scnr.nextLine().split(" \\| ");
                Task task = null;
                switch (TaskType.valueOf(str[1])) {
                    case T:
                        task = new Todo(Integer.valueOf(str[0]), str[3], Boolean.valueOf(str[2]));
                        break;
                    case E:
                        task = new Event(Integer.valueOf(str[0]), str[3], str[4], Boolean.valueOf(str[2]));
                        break;
                    case D:
                        task = new Deadline(Integer.valueOf(str[0]), str[3], str[4], Boolean.valueOf(str[2]));
                        break;
                }
                tasks.add(task);
            }
            scnr.close();
        } catch (IOException ex){
            new DukeFileException(MessageConstants.TASK_GET_ERROR);
        }
        return tasks;
    }
}
