package Application.Commands;

import Domain.Aggregates.Tracker.Task;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeExistedException;
import Domain.Repositories.ITaskRepository;

public class AddTaskCommand extends Command{
    private final Task task;
    private final ITaskRepository _taskRepository;

    public AddTaskCommand(Tracker tracker, Task task, ITaskRepository taskRepository) {
        super(tracker);
        this.task = task;
        this._taskRepository = taskRepository;
    }

    @Override
    public void execute() {

    }

    @Override
    public void validate() throws DukeExistedException {
        _taskRepository.validateTask(tracker.tasks, task);
    }
}
