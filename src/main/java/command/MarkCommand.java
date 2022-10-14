package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class MarkCommand extends Command {

    public int markIndex;

    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    /**
     * Given task index, this method will mark this specific task as done in the tasklist.
     * After marking the task as done, messages will be shown to user
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.myTaskList.get(markIndex).markDone();
        ui.showMessage("Nice! I've marked this task not done:");
        ui.showMessage(tasks.myTaskList.get(markIndex).toString());
    }
}
