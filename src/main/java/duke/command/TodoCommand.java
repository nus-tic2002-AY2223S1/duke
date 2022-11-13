package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.SaveException;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Create a todo task and add it to the list
 */
public class TodoCommand extends Command {
    public TodoCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        tasks.addTask(new ToDo(arguments.get("payload")));
        ui.printNewTask(tasks);
    }

    @Override
    public String executeGui() throws DukeException, InvalidInputException, SaveException, IOException {
        tasks.addTask(new ToDo(arguments.get("payload")));
        Task task = tasks.get(tasks.size()-1);

        return String.format("Got it. I've added this task" + task);

    }
}
