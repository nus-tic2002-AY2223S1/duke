package command;

import ui.Ui;
import storage.Storage;
import task.TaskList;
import task.Event;

public class EventCommand extends Command {

    public String description;
    public String atDate;

    public EventCommand(String description, String atDate) {
        this.description = description;
        this.atDate = atDate;
    }

    /**
     * Given task description and event datetime, this method will add a new event task in the tasklist.
     * After adding the task, messages will be shown to user
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(this.description, this.atDate));
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(tasks.myTaskList.get(tasks.myTaskList.size() - 1).toString());
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
