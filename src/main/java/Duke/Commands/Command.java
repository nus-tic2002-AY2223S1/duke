package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public abstract String execute(Storage storage, TaskList task);
}
