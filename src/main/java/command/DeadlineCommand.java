package command;

import java.time.format.DateTimeFormatter;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import task.Deadline;
import ui.Ui;

public class DeadlineCommand extends Command{
    String task;
    String date;
    /**
     * DeadlineCommand constructor
     */
    public DeadlineCommand(String task,String date) {
        this.task=task;
        this.date=date;

    }
    /**
     * Add deadline task, display result and save
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.addTask(new Deadline(task,date));
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
