package commands;

import storage.Storage;
import taskList.Task;
import taskList.TaskList;
import ui.UI;

import java.util.ArrayList;
import java.util.List;

import static ui.UI.printLine;


public class ListCommand extends Command {
    public static List<Task> taskList = new ArrayList<>();
    public static final String COMMAND_WORD = "list";

    public void execute(TaskList task, UI ui, Storage storage) {
        if (!task.checkEmptyTaskList()){
            int taskCount = 0;
            printLine();
            System.out.println("Here are the tasks in your list:");
            for (Task taskItem : taskList) {
                System.out.println(taskCount + 1 + ". " + taskItem);
                taskCount++;
            }
        }
        printLine();
    }

    @Override
    public boolean isExit(Command command) {
        return false;
    }
}
