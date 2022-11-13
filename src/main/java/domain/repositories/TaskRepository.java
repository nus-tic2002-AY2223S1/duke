package domain.repositories;

import application.helpers.MessageConstants;
import application.helpers.StorageConstants;
import domain.aggregates.tracker.Task;
import domain.aggregates.tracker.Todo;
import domain.aggregates.tracker.Event;
import domain.aggregates.tracker.Deadline;
import domain.aggregates.tracker.TaskType;
import domain.exceptions.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class TaskRepository implements ITaskRepository {

    public TaskRepository() {
    }

    /**
     * Retrieves first item by ID.
     *
     * @param tasks ArrayList<Task>.
     * @param id integer.
     * @return Optional<Task>.
     */
    public Optional<Task> getItem(ArrayList<Task> tasks, int id){
        return tasks.stream().filter(x -> x.getId() == id).findFirst();
    }

    /**
     * Retrieves first item by item.
     *
     * @param tasks ArrayList<Task>.
     * @param task Task.
     * @return Optional<Task>.
     */
    public Optional<Task> getItem(ArrayList<Task> tasks, Task task){
        return tasks.stream().filter(x -> x.equals(task)).findFirst();
    }

    /**
     * Retrieves last item ID.
     *
     * @param tasks ArrayList<Task>.
     * @return Integer.
     */
    public int getLastId(ArrayList<Task> tasks){
        if(tasks.size() == 0) {
            return 0;
        }
        return tasks.get(tasks.size() - 1).getId();
    }

    /**
     * Validates Task by index passed where Id is > 0 and < last item Id.
     *
     * @param tasks ArrayList<Task>.
     * @param id Integer.
     * @return Task.
     * @throws DukeNotFoundException if task does not exist.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public Task validateTask(ArrayList<Task> tasks, int id) throws DukeNotFoundException, DukeArgumentException {
        if(id < 0) {
            throw new DukeArgumentException(MessageConstants.TASK_ARGUMENT_SIZE_ERROR);
        } else if(id == 0 || id > getLastId(tasks)) {
            throw new DukeArgumentException(MessageConstants.TASK_ARGUMENT_SIZE_ERROR);
        }
        Optional<Task> task = getItem(tasks, id);
        if(task.isEmpty()) {
            throw new DukeNotFoundException(MessageConstants.TASK_NOT_FOUND_ERROR);
        }
        return task.get();
    }

    /**
     * Validates Task by task to make sure there are no duplicates.
     *
     * @param tasks ArrayList<Task>.
     * @param task Task.
     * @throws DukeExistedException if task already exists.
     */
    public void validateTask(ArrayList<Task> tasks, Task task) throws DukeExistedException {
        if(!getItem(tasks, task).isEmpty()) {
            throw new DukeExistedException(MessageConstants.TASK_EXISTED_ERROR);
        }
    }

    /**
     * Converts .txt file items to an ArrayList<Task> and returns it. Loads the file by given path and skips the first line which is the Header. Each line is converted and added.
     *
     * @return ArrayList<Task>.
     * @throws DukeFileException if IOException is thrown while reading file.
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
