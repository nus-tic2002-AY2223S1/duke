package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(index);
        ui.printBox("Task completed: " + tasks.getTask(index));
    }
}
