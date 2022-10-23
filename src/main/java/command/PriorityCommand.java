package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class PriorityCommand extends Command {

    public String priority;
    public int taskIndex;

    /**
     * Priority command constructor
     *
     * @param priority  task priority
     * @param taskIndex task index to set priority
     */
    public PriorityCommand(String priority, int taskIndex) {
        this.priority = priority;
        this.taskIndex = taskIndex;
    }

    /**
     * Given task index and priority, this method will change the priority of the task.
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.myTaskList.get(taskIndex).setPriority(priority);
        ui.showMessage("Noted. I've set the task priority: " + this.priority);
    }
}
