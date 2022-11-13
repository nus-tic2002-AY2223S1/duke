package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

import java.util.ArrayList;

/**
 * Delete all tasks from the task list.
 */
public class DeleteAllCommand extends Command {

    /**
     *  execute of DeleteAllCommand
     *
     * @param storage will store task list data to hard disk
     * @param taskList to delete all task
     * @return a string to show user of the command executed result
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        ArrayList<Task> listOftask = taskList.getListOfTask();

        if(listOftask.size() == 0){
            return "Task list is already empty";
        }
        listOftask.clear();

        return "All task deleted";
    }
}
