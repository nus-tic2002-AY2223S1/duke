package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;

/**
 * Deletes a task to the task list.
 */
public class DeleteCommand extends Command {
    private final int input;

    public DeleteCommand(int input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        Task currTask = taskList.getTask(input-1);
        String output = "Noted. I've removed this task:\n";
        output = output.concat("  " + currTask.getDescription() + "\n");
        taskList.removeTask(currTask);
        output = output.concat("Now you have " + taskList.getSize() + " tasks in the list.");

        return output;
    }
}
