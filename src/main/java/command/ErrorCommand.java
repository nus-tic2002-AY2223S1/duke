package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class ErrorCommand extends Command {
    public String inputCommand;

    public ErrorCommand(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Try another command, your input does not work: " + this.inputCommand);

    }
}
