package commands;

import storage.Storage;
import taskList.Task;
import taskList.TaskList;
import ui.UI;


public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final Task toAdd;
    protected String description;


    public EventCommand(String description){
        this.description = description;
        this.toAdd = new Task(description);
    }

    public void execute(TaskList task, UI ui, Storage storage) {
        taskList.eventTask(this.description);
    }

    @Override
    public boolean isExit(Command command) {
        return false;
    }
}
