package duke.command;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import duke.DateTime;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.task.Task;

// Find tasks at a specific date and print out the list
public class SearchdateCommand extends Command {
    public SearchdateCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException {
        String payload = arguments.get("payload");
        if (payload.length() == 0) {
            // Didn't input a date for parse
            throw new InvalidInputException(InputExceptionType.NO_SEARCH_DATE);
        }
        try {
            DateTime dateTime = new DateTime(payload);
            TaskList result = new TaskList(null);
            for (Task task : tasks) {
                if (task.isSameDate(dateTime)) {
                    result.add(task);
                }
            }
            ui.printTaskList(result, dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(InputExceptionType.MALFORMED_DATE, e);
        }
    }
}