package commands;

import storage.Storage;
import taskList.Task;
import taskList.TaskList;
import ui.UI;


public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final Task toAdd;
    protected String description;
    protected String deadlineDate;

    public DeadlineCommand(String description){
        this.description = description;
        this.toAdd = new Task(description);
    }

    public void execute(TaskList task, UI ui, Storage storage) {
        taskList.deadlineTask(this.description);
    }

    @Override
    public boolean isExit(Command command) {
        return false;
    }
}
