package Duke.Commands;

import Duke.Tasks.TaskList;
import Duke.Storage.Storage;
import Duke.Tasks.Task;


/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        taskList.addTask(task);
        return "Got it! I have added this task to your list:\n  "
                + task.getDescription()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";
    }
}
