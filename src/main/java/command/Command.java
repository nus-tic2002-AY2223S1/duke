package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class Command {

    public boolean exitValue = false;

    Command() {
    }

    Command(boolean exitValue) {
        this.exitValue = exitValue;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    public boolean isExit() {
        return this.exitValue;
    }
}
