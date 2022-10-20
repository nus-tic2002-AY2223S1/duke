package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.exception.SaveException;
import duke.task.Task;

// Mark a task in the task list as done
public class UnmarkCommand extends Command {
    public UnmarkCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        String indexString = arguments.get("payload");
        if (indexString == null) {
            // An index must be provided for the task to be marked "done"
            throw new InvalidInputException(InputExceptionType.EMPTY_INDEX);
        } else {
            try {
                int index = Integer.parseInt(indexString);
                if (index > tasks.size() || index < 1) {
                    // This index is out of the boundary of our database
                    throw new InvalidInputException(InputExceptionType.INDEX_OUT_OF_BOUND);
                }

                Task task = tasks.get(index - 1);
                task.markAsnotDone();
                tasks.setTask(index - 1, task);

                ui.print("Nice! I've marked this task as ndone:");
                ui.print("\t" + task);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(InputExceptionType.NOT_INTEGER, e);
            }
        }
    }
}
