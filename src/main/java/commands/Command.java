package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public abstract class Command {
    private Command command;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return ByeCommand.exit();
    }
}
