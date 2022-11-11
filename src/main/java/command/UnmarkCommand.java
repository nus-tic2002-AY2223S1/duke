package command;


import exceptions.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command{
    int item;
    /**
     * Constructor to get item value
     */
    public UnmarkCommand(String item){
        this.item=Integer.parseInt(item)-1;
    }
    /**
     * Execute unmark, display result and save
     */
    public void execute(TaskList tasks, Ui ui, Storage storage)throws DukeException {

        tasks.tasks.get(item).unmark();
        ui.display("Nice! I've marked this task as done:\n"+tasks.tasks.get(item).toString());
        try {
            storage.save(tasks.tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

}
