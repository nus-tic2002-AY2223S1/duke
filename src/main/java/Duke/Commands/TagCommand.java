package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

/**
 * Add tagging to a task in the task list.
 */
public class TagCommand extends Command {
    private final int input;
    private final String tag;

    /**
     *  Constructor of TagCommand
     *
     * @param input is the task # selected for tagging
     * @param tag  to be added to the selected task
     */
    public TagCommand(int input, String tag) {
        this.input = input;
        this.tag = tag;
    }
    /**
     *  execute of TagCommand
     *
     * @param storage will store task list data to hard disk
     * @param taskList to retrieve task to mark as complete
     * @return a string to show user of the command executed result
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        if (taskList.getSize() < input){
            return "Task list does not have #" + input + ", please select a number within the task list";
        }
        Task currTask = taskList.getTask(input-1);
        currTask.addTagging(tag);
        return currTask.getDescription();
    }
}
