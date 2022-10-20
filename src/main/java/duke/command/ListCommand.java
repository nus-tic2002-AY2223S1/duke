package duke.command;

import java.util.HashMap;

import duke.TaskList;
import duke.Ui;

// Print out everything in the list, index starts from 1
public class ListCommand extends Command {
    public ListCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() {
        if (tasks.size() == 0) {
            ui.print("You don't have a task in your list!");
            return;
        }
        ui.printTaskList(tasks);
    }
}