package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Task;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;

public class AddTaskCommand extends Command{
    private final Task task;

    /**
     * Add Task command default constructor
     * Requires Tracker, Storage and Task
     */
    public AddTaskCommand(Tracker tracker, Storage storage, Task task) {
        super(tracker, storage);
        this.task = task;
    }

    /**
     * Abstract method that is overwritten
     * Handles the add capability - Adding to Tracker and Appending to local data file
     */
    @Override
    public void execute() throws DukeExistedException, DukeFileException {
        if(this.tracker.hasItemAdded(task)) {
            this.storage.saveItem(task.toString());
        }
    }
}
