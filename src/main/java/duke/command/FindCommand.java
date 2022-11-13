package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.exception.SaveException;
import duke.task.Task;

/**
 * Find a task in the tasklist
 */
public class FindCommand extends Command {
    public FindCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException {
        String needle = arguments.get("payload");
        if (needle.length() == 0) {
            throw new InvalidInputException(InputExceptionType.EMPTY_DESCRIPTION);
        }
        TaskList result = new TaskList(null);
        for (Task task : tasks) {
            if (task.toString().contains(needle)) {
                result.add(task);
            }
        }
        ui.printTaskList(result);
    }

    @Override
    public String executeGui() throws InvalidInputException, IOException, SaveException {
        String needle = arguments.get("payload");
        TaskList result = new TaskList(null);
        String text = "Here are the tasks in your list:\n";
        for (Task task : tasks) {
            if (task.toString().contains(needle)) {
                result.add(task);

            }
        }
        ui.printTaskList(result);
        for (int i = 0; i < result.size(); i++) {
            text += i+1 + "." + result.get(i) +"\n";
        }
        return text;

    }
}
