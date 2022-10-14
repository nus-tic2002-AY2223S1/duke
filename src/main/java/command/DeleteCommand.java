package command;

import ui.Ui;
import storage.Storage;
import task.TaskList;

public class DeleteCommand extends Command {
    public int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Given the task index, this method will delete that specific task in myTaskList.
     * After deleting the task, messages will be shown to user
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(tasks.myTaskList.get(deleteIndex).toString());
        tasks.myTaskList.remove(deleteIndex);
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
