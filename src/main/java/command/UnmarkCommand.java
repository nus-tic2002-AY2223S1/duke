package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class UnmarkCommand extends Command {

    public int unMarkIndex;

    /**
     * Unmark command constructor
     *
     * @param unMarkIndex task index to mark task undone in tasklist
     */
    public UnmarkCommand(int unMarkIndex) {
        this.unMarkIndex = unMarkIndex;
    }

    /**
     * Given the task index, this method will help to mark this specific task in the tasklist as not done.
     * After marking it not done, messages will be shown to user
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.myTaskList.get(unMarkIndex).unMarkDone();
        ui.showMessage("Nice! I've marked this task not done:");
        ui.showMessage(tasks.myTaskList.get(unMarkIndex).toString());
    }
}
