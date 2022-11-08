package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Task;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;

public class AddTaskCommand extends Command{
    private final Task task;

    /**
     * Initialises AddTaskCommand.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     * @param task Task.
     */
    public AddTaskCommand(Tracker tracker, Storage storage, Task task) {
        super(tracker, storage);
        this.task = task;
    }

    /**
     * @inheritDoc
     * Handles add task and Appends to local data file.
     */
    @Override
    public void execute() throws DukeExistedException, DukeFileException {
        if(this.tracker.hasItemAdded(task)) {
            this.storage.saveItem(task.toString());
        }
    }
}
