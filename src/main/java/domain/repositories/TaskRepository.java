package domain.repositories;

import application.helpers.MessageConstants;
import application.helpers.StorageConstants;
import domain.aggregates.tracker.Task;
import domain.aggregates.tracker.Todo;
import domain.aggregates.tracker.Event;
import domain.aggregates.tracker.Deadline;
import domain.aggregates.tracker.TaskType;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class TaskRepository implements ITaskRepository {

    public TaskRepository() {
    }

    /**
     * Find first item by ID
     */
    public Optional<Task> getItem(ArrayList<Task> tasks, int id){
        return tasks.stream().filter(x -> x.getId() == id).findFirst();
    }

    /**
     * Find first item by item
     */
    public Optional<Task> getItem(ArrayList<Task> tasks, Task task){
        return tasks.stream().filter(x -> x.equals(task)).findFirst();
    }

    /**
     * Find last item ID
     */
    public int getLastId(ArrayList<Task> tasks){
        if(tasks.size() == 0) {
            return 0;
        }
        return tasks.get(tasks.size() - 1).getId();
    }

    /**
     * Validate Task by index passed where is > 0 and < last item ID
     * Return Task object if successfully validated
     * Else, DukeValidationException thrown with respective error messages
     */
    public Task validateTask(ArrayList<Task> tasks, int id) throws DukeNotFoundException, DukeValidationException {
        if(id < 0) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Id"));
        } else if(id == 0 || id > getLastId(tasks)) {
            throw new DukeValidationException(MessageConstants.TASK_VALIDATION_SIZE_ERROR);
        }
        Optional<Task> task = getItem(tasks, id);
        if(task.isEmpty()) {
            throw new DukeNotFoundException(MessageConstants.TASK_NOT_FOUND_ERROR);
        }
        return task.get();
    }

    /**
     * Validate Task by task to make sure there are no duplicates
     * If exists, DukeExistedException is thrown
     */
    public void validateTask(ArrayList<Task> tasks, Task task) throws DukeExistedException {
        if(!getItem(tasks, task).isEmpty()) {
            throw new DukeExistedException(MessageConstants.TASK_EXISTED_ERROR);
        }
    }

    /**
     * Convert .txt file items to an ArrayList<Task> and returns it
     * Loads the file by given path and skips the first line which is the Header
     * Each line is converted and added
     * If IOException is thrown while reading the file, DukeFileException is thrown with respective error message
     */
    public ArrayList<Task> convertToTaskList() throws DukeFileException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            Scanner scanner = new Scanner(new FileReader(StorageConstants.FILE_PATH));
            String[] rows;
            scanner.nextLine();
            while (scanner.hasNext()) {
                rows = scanner.nextLine().split(" \\| ");
                Task task = null;
                switch (TaskType.valueOf(rows[1])) {
                    case T:
                        task = new Todo(Integer.valueOf(rows[0]), rows[3], Boolean.valueOf(rows[2]));
                        break;
                    case E:
                        task = new Event(Integer.valueOf(rows[0]), rows[3], rows[4], Boolean.valueOf(rows[2]));
                        break;
                    case D:
                        task = new Deadline(Integer.valueOf(rows[0]), rows[3], rows[4], Boolean.valueOf(rows[2]));
                        break;
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (IOException ex){
            throw new DukeFileException(MessageConstants.TASK_GET_ERROR);
        }
        return tasks;
    }
}
