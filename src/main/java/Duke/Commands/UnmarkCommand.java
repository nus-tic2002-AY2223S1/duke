package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

/**
 * Mark a task as not completed in the task list.
 */
public class UnmarkCommand extends Command {
    private final int input;

    public UnmarkCommand(int input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        Task currTask = taskList.getTask(input-1);
        currTask.unmarkTask();
        return currTask.getDescription();
    }
}
