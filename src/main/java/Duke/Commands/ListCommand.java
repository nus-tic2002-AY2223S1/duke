package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

import java.util.ArrayList;


/**
 * List all tasks from the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(Storage storage, TaskList taskList) {
        ArrayList<Task> listOftask = taskList.getListOfTask();

        if(listOftask.size() == 0){
            return "List is empty";
        }
        String output="";

        for (int i = 0; i < listOftask.size(); i++) {
            output = output.concat((i+1 + ". " + listOftask.get(i).getDescription() + "\n"));
        }
        return output;
    }
}
