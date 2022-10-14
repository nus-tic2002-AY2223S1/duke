package command;

import task.TaskList;
import task.Deadline;
import ui.Ui;
import storage.Storage;

public class DeadlineCommand extends Command {

    public String description;
    public String byDate;

    public DeadlineCommand(String description, String byDate) {
        this.description = description;
        this.byDate = byDate;
    }

    /**
     * Given task description and deadline, this method will add a new deadline task in the task list.
     * After adding the task, messages will be shown to user
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Got it. I've added this task:");
        tasks.myTaskList.add(new Deadline(description, byDate));
        ui.showMessage(tasks.myTaskList.get(tasks.myTaskList.size() - 1).toString());
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
