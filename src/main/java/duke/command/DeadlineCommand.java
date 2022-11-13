package duke.command;

import java.io.IOException;

import java.util.HashMap;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.exception.SaveException;
import duke.task.Deadline;
import duke.DateTime;
import java.time.format.DateTimeParseException;

/**
 * Insert a deadline task into the task list
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        String by = arguments.get("by");
        if (by == null || by.length() == 0) {
            // Either /at is not found at all, or no dates are following /at
            throw new InvalidInputException(InputExceptionType.NO_BY_DATE);
        }
        try {
            tasks.addTask(new Deadline(arguments.get("payload"), new DateTime(by)));
            ui.printNewTask(tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(InputExceptionType.MALFORMED_DATE);
        }
    }

    @Override
    public String executeGui() throws DukeException, InvalidInputException {
        String by = arguments.get("by");
        if (by == null || by.length() == 0) {
            // Either /at is not found at all, or no dates are following /at
            throw new InvalidInputException(InputExceptionType.NO_BY_DATE);
        }
        try {
            tasks.addTask(new Deadline(arguments.get("payload"), new DateTime(by)));
        } catch (DateTimeParseException | IOException | SaveException e) {
            throw new InvalidInputException(InputExceptionType.MALFORMED_DATE);
        }
        return "ok! I've add this task with deadline";
    }
}
