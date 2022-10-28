package catbot.command;

import catbot.Storage;
import catbot.task.TaskList;
import catbot.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        ui.showMessage("Cat dont understand what human wants");
    }
}
