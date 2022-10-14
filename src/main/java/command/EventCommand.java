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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(this.description, this.atDate));
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(tasks.myTaskList.get(tasks.myTaskList.size() - 1).toString());
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
