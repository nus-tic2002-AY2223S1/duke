package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}


