package commands;

import storage.Storage;
import taskList.TaskList;
import ui.UI;

public class Command {
    Command() {
    }

    public boolean isExit = false;

    Command(boolean exitValue) {
        this.isExit = exitValue;
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public boolean isExit() {
        return false;
    }

}
