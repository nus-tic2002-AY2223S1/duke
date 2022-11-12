package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBox("Task " + tasks.getTask(index) + " is deleted.");
        tasks.delete(index);
    }
}
