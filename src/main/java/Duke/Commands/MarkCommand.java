package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;

/**
 * Mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private final int input;

    public MarkCommand(int input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        Task currTask = taskList.getTask(input-1);
        currTask.markTask();
        return currTask.getDescription();
    }
}
