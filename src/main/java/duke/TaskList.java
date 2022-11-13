package duke;

import java.io.IOException;
import java.util.Vector;

import duke.exception.SaveException;
import duke.task.Task;


/**
 * A collection class for storing the list of tasks.
 * The entire task list will be automatically saved when there are any changes.
 */
public class TaskList extends Vector<Task> {
    public static final long serialVersionUID = 1L;
    protected Storage storage;

    /**
     * Constructor of the task list, set storage handler to null
     */
    public TaskList() {
        this(null);
    }

    /**
     * Constructor of the task list
     * @param storage The storage handler which will be used whenever the list is changed
     */
    public TaskList(Storage storage) {
        super();
        setStorage(storage);
    }

    /**
     * Setter for the storage attribute
     * @param storage The storage handler to be used for replacing the current attribute
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * If the storage handler is not null, save the list to local file
     * @throws IOException This is thrown when object writing encounters an error
     * @throws SaveException This is thrown when being failed to open the save file
     */
    protected void save() throws IOException, SaveException {
        if (storage != null) {
            storage.save(this);
        }
    }

    /**
     * Add a task to the list, save on completion
     * @param task Task to be added
     * @throws IOException This is thrown when object writing encounters an error
     * @throws SaveException This is thrown when being failed to open the save file
     */
    public void addTask(Task task) throws IOException, SaveException {
        super.add(task);
        save();
    }
    public void tagTask(int index , Task task) throws IOException, SaveException {
        super.add(index , task);
        save();
    }

    /**
     * Replace a task in the list, save on completion
     * @param index Index of the task to be replaced, start at 0
     * @param task Task to be used for replacement
     * @throws IOException This is thrown when object writing encounters an error
     * @throws SaveException This is thrown when being failed to open the save file
     */
    public void setTask(int index, Task task) throws IOException, SaveException {
        super.set(index, task);
        save();
    }

    /**
     * Delete a task in the list and return it for the last time, save on completion
     * @param index Index of the task to be deleted, start at 0
     * @return The deleted task
     * @throws IOException This is thrown when object writing encounters an error
     * @throws SaveException This is thrown when being failed to open the save file
     */
    public Task deleteTask(int index) throws IOException, SaveException {
        Task task = super.remove(index);
        save();
        return task;
    }
}
