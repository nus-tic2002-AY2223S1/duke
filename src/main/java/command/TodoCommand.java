package command;


import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class TodoCommand extends Command {
    String task;
    /**
     * TOdoCommand constructor
     */
    public TodoCommand(String task) {
        this.task=task;

    }
    /**
     * Add todo task, display result and save
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Todo(task));
        ui.display("Got it. I've added this task:");
        ui.display(tasks.tasks.get(tasks.tasks.size()-1).toString());
        ui.display("Now you have " + (tasks.tasks.size()) + " tasks in the list.");
        try {
            storage.save(tasks.tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
