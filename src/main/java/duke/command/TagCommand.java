package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.exception.SaveException;
import duke.task.Tag;
import duke.task.Task;

/**
 * Create a tag task and add it to the list
 */
public class TagCommand extends Command {
    public TagCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        String tag = arguments.get("tag");
        String indexString = arguments.get("payload");
        int index = Integer.parseInt(indexString);
        if (tag == null || tag.length() == 0) {
            // Either /at is not found at all, or no dates are following /at
            throw new InvalidInputException(InputExceptionType.NO_AT_DATE);
        }


        Task task = tasks.get(index - 1);
        new Tag(tag);
        task.tagD();
        tasks.setTask(index-1,task);
        tasks.get(index-1);
        ui.printNewTask(tasks);
        ui.print("Nice! I've add tag to this task:");
        ui.print("\t" + task);
    }

    @Override
    public String executeGui() throws InvalidInputException, IOException, SaveException {
        String tag = arguments.get("tag");
        String indexString = arguments.get("payload");
        int index = Integer.parseInt(indexString);
        if (tag == null || tag.length() == 0) {
            // Either /at is not found at all, or no dates are following /at
            throw new InvalidInputException(InputExceptionType.NO_AT_DATE);
        }


        Task task = tasks.get(index - 1);
        new Tag(tag);
        task.tagD();
        tasks.setTask(index-1,task);
        tasks.get(index-1);
        return "nice I have tag this task ";
    }
}