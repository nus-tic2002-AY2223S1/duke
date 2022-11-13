package entity;

import exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Storage extends TaskList {
    private final String filePath;

    private static final Storage instance = new Storage();

    public static Storage getInstance() {
        return instance;
    }

    /**
     * Storage object constructor
     */
    private Storage() {
        this.filePath = "data/duke.txt";
        create();
    }

    /**
     * Create text file for storage if file not exists
     */
    public void create() {
        try {
            Path p = Paths.get(this.filePath);
            Files.createDirectories(p.getParent());
            File file = new File(this.filePath);
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.out.println("hahahahah: "+ this.filePath);
            throw new DukeException("☹ OOPS!!! File create error.");
        }
    }

    /**
     * Write Tasks to file
     *
     * @param contexts Tasks
     * @throws DukeException Duke exception
     */
    public void write(String contexts) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(contexts);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! File write error.");
        }
    }

    /**
     * Read tasks in file
     *
     * @throws DukeException Duke exception
     */
    public void read() throws DukeException {
        try {
            File file = new File(this.filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                instance.loadTask(generateTask(line));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! File read error.");
        }

    }

    /**
     * Parse task in file to TasksList object
     *
     * @param line each line of task
     * @return a Task object
     */
    public Task generateTask(String line) {
        String[] rawDetails = line.split(" \\|", -1);
        String[] details = Arrays.stream(rawDetails).map(String::trim).toArray(o -> new String[rawDetails.length]);
        Task task = null;
        switch (details[0]) {
            case "T":
                task = new Todo(details[3]);
                if (Objects.equals(details[1], "1")) task.markTask();
                if (!Objects.equals(details[2], "")) task.addTags(List.of(CommandParser.getTags(details[2])));
                break;
            case "D":
                task = new Deadline(details[3],
                        LocalDateTime.parse(details[4].replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (Objects.equals(details[1], "1")) task.markTask();
                if (!Objects.equals(details[2], "")) task.addTags(List.of(CommandParser.getTags(details[2])));
                break;
            case "E":
                task = new Event(details[3],
                        LocalDateTime.parse(details[4].replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (Objects.equals(details[1], "1")) task.markTask();
                if (!Objects.equals(details[2], "")) task.addTags(List.of(CommandParser.getTags(details[2])));

                break;
        }
        return task;
    }

}
