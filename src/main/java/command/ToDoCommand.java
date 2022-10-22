package command;

import task.TaskList;
import task.ToDo;
import ui.Ui;
import storage.Storage;

public class ToDoCommand extends Command {

    public String description;

    /**
     * To do command constructor
     *
     * @param description task description
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Given task description, this method will add a new todo task in the tasklist.
     * After adding the task, messages will be shown to user
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new ToDo(this.description));
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(this.description);
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
