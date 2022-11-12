package Duke.Commands;

import Duke.Common.Messages;
import Duke.Tasks.TaskList;
import Duke.Storage.Storage;

/**
 * To terminates the program
 */
public class ExitCommand extends Command{
    public String execute(Storage storage, TaskList taskList) {
        return Messages.MESSAGE_GOODBYE;
    }
}
