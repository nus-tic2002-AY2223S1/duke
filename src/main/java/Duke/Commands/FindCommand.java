package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        ArrayList<Task> listOftask = taskList.getListOfTask();

        if(listOftask.isEmpty()){
            return "Task list is empty, no searchable task\n";
        }

        String output="";

        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).getDescriptionOnly().contains(search))
                output = output.concat(i+1 + ". " + taskList.getTask(i).getDescription() + "\n");
        }

        if (output.isEmpty()){
            return "No result found\n";
        }

        return output;
    }
}