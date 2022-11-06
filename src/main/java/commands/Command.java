package commands;

import storage.Storage;
import taskList.TaskList;
import ui.UI;

public abstract class Command {
    protected Command() {
    }

    protected TaskList taskList;

    public boolean isExit = false;

    Command(boolean exitValue) {
        this.isExit = exitValue;
    }



    public static boolean isExit() {
        return false;
    }

    public abstract boolean isExit(Command command);
}
