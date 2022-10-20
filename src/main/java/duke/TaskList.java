package duke;

import java.io.IOException;
import java.util.Vector;

import duke.exception.SaveException;
import duke.task.Task;

public class TaskList extends Vector<Task> {
    public static final long serialVersionUID = 1L;
    protected Storage storage;

    public TaskList(Storage storage) {
        super();
        setStorage(storage);
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /*
     * Save file on all the modification actions
     */
    public void addTask(Task task) throws IOException, SaveException {
        super.add(task);
        storage.save(this);
    }

    public void setTask(int index, Task task) throws IOException, SaveException {
        super.set(index, task);
        storage.save(this);
    }

    public Task deleteTask(int index) throws IOException, SaveException {
        Task task = super.remove(index);
        storage.save(this);
        return task;
    }
}
