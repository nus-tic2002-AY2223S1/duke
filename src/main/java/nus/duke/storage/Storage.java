package nus.duke.storage;

import nus.duke.data.DukeException;
import nus.duke.tasklist.Task;
import nus.duke.tasklist.TaskList;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.io.File;

/**
 * Load and save task to/from the .txt file.
 *
 */
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

    /**
     * Load TaskList from the path provided by the user. If no file path provided, the default path will be used.
     *
     * @return Return the TaskList loaded from the .txt file or a new TaskList if the path is invalid.
     * @throws DukeException when the .txt file cannot be read.
     */
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
    /**
     * Save TaskList to the .txt file when there is a change to the TaskList, E.g. adding or deleting tasks.
     * Create new file when the file path provided is invalid or the file format is incorrect.
     *
     * @param toSave is the TaskList that should be written into the .txt file.
     * @throws DukeException when creating new .txt file failed or when writing to file failed.
     */
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
            Task task = (Task) toSave.getList().get(i);
            toWrite = task.toString(2) + "\n";
            try {
                Files.write(path, toWrite.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }

}