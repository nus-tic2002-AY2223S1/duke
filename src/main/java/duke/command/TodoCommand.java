package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;
import duke.exception.SaveException;
import duke.task.ToDo;

// Create a todo task
public class TodoCommand extends Command {
    public TodoCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        tasks.addTask(new ToDo(arguments.get("payload")));
        ui.printNewTask(tasks);
    }
}
