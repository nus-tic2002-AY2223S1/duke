package command;


import exceptions.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    int item;
    /**
     * DeleteCommand constructor
     */
    public DeleteCommand(String item) {
        this.item = Integer.parseInt(item) - 1;
    }
    /**
     * delete task, display result and save
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.display("Noted. I've removed this task:");
        ui.display(tasks.tasks.get(item).toString());
        tasks.removeTask(item);
        ui.display("Now you have " + tasks.tasks.size() + " tasks in the list.");
        try {
            storage.save(tasks.tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }

    }

}
