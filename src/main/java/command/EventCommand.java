package command;


import storage.Storage;
import task.TaskList;
import task.Event;
import ui.Ui;

public class EventCommand extends Command{
    private String task;
    private String date;

    /**
     * EventCommand constructor
     */
    public EventCommand(String task,String date) {
        this.task=task;
        this.date=date;

    }
    /**
     * Add event task, display result and save
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(task,date));
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
