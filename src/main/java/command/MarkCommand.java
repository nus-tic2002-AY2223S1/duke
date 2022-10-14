package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class MarkCommand extends Command {

    public int markIndex;

    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.myTaskList.get(markIndex).markDone();
        ui.showMessage("Nice! I've marked this task not done:");
        System.out.println(tasks.myTaskList.get(markIndex).toString());
    }
}
