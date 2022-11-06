package command;

import storage.Storage;
import task.Task;
import task.Event;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class FindtaskCommand extends Command {

    public String inputDatetime;

    /**
     * Find task command constructor
     *
     * @param inputDatetime task datetime
     */
    public FindtaskCommand(String inputDatetime) {
        this.inputDatetime = inputDatetime;
    }

    /**
     * Given a specific datetime, this method will search through myTaskList and find tasks that is either event or deadline and has that datetime.
     * These task would be shown to user.
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filterList = new ArrayList<>();
        if (tasks.myTaskList.size() == 0) {
            ui.showMessage("There is no task currently");
        } else {
            for (Task task : tasks.myTaskList) {
                if ((task instanceof Event && ((Event) task).getAt().equals(this.inputDatetime)) || (task instanceof Deadline && ((Deadline) task).getBy().equals(this.inputDatetime))) {
                    filterList.add(task);
                }
            }
        }
        if (filterList.size() == 0) {
            ui.showMessage("There is no matching event or deadline task matching the datetime given.");
        } else {
            for (int count = 0; count < filterList.size(); count++) {
                ui.showMessage((count + 1) + "." + filterList.get(count).toString());
            }
        }
    }
}
