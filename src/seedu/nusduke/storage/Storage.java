package seedu.nusduke.storage;

import seedu.nusduke.data.DukeException;
import seedu.nusduke.tasklist.TaskList;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.io.File;

public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "tasklist.txt";

    public final Path path;

    public Storage(){
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath){
        path = Paths.get(filePath);
    }

    public TaskList load() throws DukeException {
        if (!Files.exists(path) || !Files.isRegularFile(path)){
            return new TaskList();
        }
        TaskList tasks = new TaskList();
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        for (String line: lines) {
            tasks.loadTasksFromFiles(DecodeTasks.decodeTasks(line));
        }
        return tasks;
    }

    public void save (TaskList toSave) throws DukeException {
        if (!Files.exists(path) || !Files.isRegularFile(path)){
            File taskList = new File(String.valueOf(path));
            try {
                taskList.createNewFile();
            } catch (IOException e){
                throw new DukeException(e.getMessage());
            }
        }
        String toWrite;
        try {
            Files.write(path, "".getBytes());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        for (int i = 0; i < toSave.getListCount(); i++){
            toWrite = toSave.getList().get(i).toString() + "\n";
            try {
                Files.write(path, toWrite.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }

}