package commands;

import storage.Storage;
import taskList.Task;
import taskList.TaskList;
import ui.UI;


public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
//    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";
    private final Task toAdd;
    protected String description;

    public TodoCommand(String description){
        this.description = description;
        this.toAdd = new Task(description);
    }

    public void execute(TaskList task, UI ui, Storage storage) {
        TaskList.todoTask(this.description);
    }
}
