package Application.Commands;

import Domain.Aggregates.Storage.Storage;
import Domain.Aggregates.Tracker.Task;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeFileException;
import Domain.Repositories.IStorageRepository;
import Domain.Repositories.ITaskRepository;

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
