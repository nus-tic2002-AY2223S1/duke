package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

/**
 * Deletes a task in the task list.
 */
public class DeleteCommand extends Command {
    private final int input;

    /**
     *  Constructor of AddCommand
     *
     * @param input is the task # to be deleted from the task list
     */
    public DeleteCommand(int input) {
        this.input = input;
    }

    /**
     *  execute of DeleteCommand
     *
     * @param storage will store task list data to hard disk
     * @param taskList to delete the selected task from
     * @return a string to show user of the command executed result
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        if (taskList.getSize() < input){
            return "Task list does not have #" + input + ", please select a number within the task list";
        }
        Task currTask = taskList.getTask(input-1);
        String output = "Noted. I've removed this task:\n";
        output = output.concat("  " + currTask.getDescription() + "\n");
        taskList.removeTask(currTask);
        output = output.concat("Now you have " + taskList.getSize() + " tasks in the list.");

        return output;
    }
}
