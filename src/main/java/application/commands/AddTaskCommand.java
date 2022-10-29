package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Task;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;

public class AddTaskCommand extends Command{
    private final Task task;

    public AddTaskCommand(Tracker tracker, Storage storage, Task task) throws DukeExistedException, DukeFileException {
        super(tracker, storage);
        this.task = task;
    }

    @Override
    public void execute() throws DukeExistedException, DukeFileException {
        if(this.tracker.addItem(task))
            this.storage.saveItem(task.toString());
    }
}
