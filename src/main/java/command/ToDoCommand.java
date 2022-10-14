package command;

import task.TaskList;
import task.ToDo;
import ui.Ui;
import storage.Storage;

public class ToDoCommand extends Command {

    public String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new ToDo(this.description));
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(this.description);
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
