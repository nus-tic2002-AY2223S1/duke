package catbot.command;

import catbot.Storage;
import catbot.task.TaskList;
import catbot.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        ui.showMessage(t.toString());
    }
}
