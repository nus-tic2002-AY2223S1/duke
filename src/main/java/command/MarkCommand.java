package command;


import exceptions.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command{
    int item;
    public MarkCommand(String item){
        this.item=Integer.parseInt(item)-1;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage)throws DukeException {

        tasks.tasks.get(item).mark();
        ui.display("Nice! I've marked this task as done:\n"+tasks.tasks.get(item).toString());
        try {
            storage.save(tasks.tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
