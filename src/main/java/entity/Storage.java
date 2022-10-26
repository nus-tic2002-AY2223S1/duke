package entity;

import exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage() throws DukeException {
        String dir = System.getProperty("user.dir");
        this.filePath = dir + "/data/duke.txt";
        create();
    }

    public void create() throws DukeException {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! File create error.");
        }
    }

    public void write(String contexts) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(contexts);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! File write error.");
        }
    }

    public void read(TaskList taskList) throws DukeException {
        try {
            File file = new File(this.filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                taskList.addTask(generateTask(line));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! File read error.");
        }

    }

    public Task generateTask(String line) {
        String[] details = line.split(" \\| ");
        Task task = null;
        switch (details[0]) {
            case "T":
                task = new Todo(details[2]);
                if (Objects.equals(details[1], "1")) task.markTask();
                break;
            case "D":
                task = new Deadline(details[2], details[3]);
                if (Objects.equals(details[1], "1")) task.markTask();
                break;
            case "E":
                task = new Event(details[2], details[3]);
                if (Objects.equals(details[1], "1")) task.markTask();
                break;
        }
        return task;
    }

}
