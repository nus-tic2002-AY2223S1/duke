package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

import java.util.ArrayList;

public class DeleteAllCommand extends Command {

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
