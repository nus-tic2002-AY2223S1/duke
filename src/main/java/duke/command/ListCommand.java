package duke.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.SaveException;
import duke.task.Task;
import static duke.Ui.INTERNAL_INDENT;


/**
 * Print out everything in the list, index starts from 1
 */
public class ListCommand extends Command {
    public ListCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() {
        ui.printTaskList(tasks);
    }

    @Override
    public String executeGui() throws InvalidInputException, IOException, SaveException {
        String text = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            text += i+1 + "." + tasks.get(i) +"\n";
        }
        return text ;
    }
}