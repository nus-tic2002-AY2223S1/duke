package duke.command;

import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.task.Task;

// Find a task in the tasklist
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
}
