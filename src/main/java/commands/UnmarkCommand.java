package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.undone(index);
        ui.printBox("Task completed: " + tasks.getTask(index));
    }
}
