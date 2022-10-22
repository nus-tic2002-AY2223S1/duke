package seedu.nusduke.storage;

import seedu.nusduke.data.DukeException;
import seedu.nusduke.tasklist.TaskList;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "data/tasklist.txt";

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

    public void save (TaskList toSave){
        String toWrite;
        for (int i = 0; i < toSave.getListCount(); i++){
            toWrite = toSave.getList().get(i).toString();
            try {
                Files.write(path, toWrite.getBytes());
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }

}