package command;

import ui.Ui;
import storage.Storage;
import task.TaskList;

public class DeleteCommand extends Command {
    public int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(tasks.myTaskList.get(deleteIndex).toString());
        tasks.myTaskList.remove(deleteIndex);
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
