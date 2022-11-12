package Duke.Commands;

import Duke.Tasks.TaskList;
import Duke.Storage.Storage;
import Duke.Tasks.Task;


/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     *  Constructor of AddCommand
     *
     * @param task is the task to be added to task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     *  execute of AddCommand
     *
     * @param storage will store task list data to hard disk
     * @param taskList to add task into
     * @return a string to show user of the command executed result
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        taskList.addTask(task);
        return "Got it! I have added this task to your list:\n  "
                + task.getDescription()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";
    }
}
