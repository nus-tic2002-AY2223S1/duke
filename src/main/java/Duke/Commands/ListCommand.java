package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

import java.util.ArrayList;


/**
 * List all tasks from the task list or list task with certain tagging.
 */
public class ListCommand extends Command {
    private final String input;

    /**
     *  Constructor of ListCommand
     *
     * @param input is the tagging keyword to find task that contains such tagging
     */
    public ListCommand(String input) {
        this.input = input;
    }

    /**
     *  execute of ListCommand
     *
     * @param storage will store task list data to hard disk
     * @param taskList to list the task from
     * @return a string to show user of the list result
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {

        ArrayList<Task> listOftask = taskList.getListOfTask();

        if(listOftask.size() == 0){
            return "Task list is empty";
        }
        String output="";

        if (input.contains("#")){
            for (int i = 0; i < listOftask.size(); i++) {
                if (listOftask.get(i).getTagging().contains(input)){
                    output = output.concat((i+1 + ". " + listOftask.get(i).getDescription() + "\n"));
                }
            }
            return output;
        }


        for (int i = 0; i < listOftask.size(); i++) {
            output = output.concat((i+1 + ". " + listOftask.get(i).getDescription() + "\n"));
        }
        return output;
    }
}
