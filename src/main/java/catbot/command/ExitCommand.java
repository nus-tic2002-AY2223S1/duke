package catbot.command;

import catbot.Storage;
import catbot.task.TaskList;
import catbot.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        exit = true;
    }
}
