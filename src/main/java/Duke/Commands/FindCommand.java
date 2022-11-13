package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

import java.util.ArrayList;

/**
 * To find tasks with a certain keyword from the task list.
 */
public class FindCommand extends Command {
    private final String search;

    /**
     *  Constructor of FindCommand
     *
     * @param search is the keyword to find the task description which contains it
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     *  execute of FindCommand
     *
     * @param storage will store task list data to hard disk
     * @param taskList to find all task with keyword
     * @return a string to show user of the search result
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        ArrayList<Task> listOftask = taskList.getListOfTask();

        if(listOftask.isEmpty()){
            return "Task list is empty, no searchable task\n";
        }

        String output="";

        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).getDescriptionOnly().contains(search)){
                output = output.concat(i+1 + ". " + taskList.getTask(i).getDescription() + "\n");
            }
        }

        if (output.isEmpty()){
            return "No result found\n";
        }

        return output;
    }
}