package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printArray(tasks.getList(), tasks.size());
    }
}
