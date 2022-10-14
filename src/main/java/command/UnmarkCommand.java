package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class UnmarkCommand extends Command {

    public int unMarkIndex;

    public UnmarkCommand(int unMarkIndex) {
        this.unMarkIndex = unMarkIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.myTaskList.get(unMarkIndex).unMarkDone();
        ui.showMessage("Nice! I've marked this task not done:");
        System.out.println(tasks.myTaskList.get(unMarkIndex).toString());
    }
}
