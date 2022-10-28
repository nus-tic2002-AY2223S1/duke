package catbot.command;

import catbot.Storage;
import catbot.task.TaskList;
import catbot.Ui;

public abstract class Command {
    protected boolean exit = false;

    public boolean isExit(){
        return exit;
    }

    public abstract void execute(TaskList t, Ui ui, Storage s);
}
