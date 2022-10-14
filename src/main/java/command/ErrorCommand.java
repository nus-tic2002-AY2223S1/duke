package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class ErrorCommand extends Command {
    public String inputCommand;

    public ErrorCommand(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * This method will alert user that their command does not make sense and would require them to try again.
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Try another command, your input does not work: " + this.inputCommand);
    }
}
