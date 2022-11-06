package command;

import ui.Ui;
import storage.Storage;
import task.TaskList;

public class ListCommand extends Command {

    /**
     * This method will list all the tasks stored in myTaskList.
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.myTaskList.size() == 0) {
            ui.showMessage("There is no task, please add some task!");
        } else {
            for (int count = 0; count < tasks.myTaskList.size(); count++) {
                ui.showMessage((count + 1) + "." + tasks.myTaskList.get(count).toString());
            }
        }
    }
}
