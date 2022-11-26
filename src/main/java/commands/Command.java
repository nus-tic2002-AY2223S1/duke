package commands;

import tasklist.TaskList;

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
