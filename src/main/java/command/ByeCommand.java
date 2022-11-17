package command;

import exceptions.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;




public class ByeCommand extends Command{
    /**
     * Exit the program
     */
    public void execute(TaskList tasks, Ui ui, Storage storage)throws DukeException {
        super.toExit=true;
        ui.display("Bye. Hope to see you again soon!");
    }
}
