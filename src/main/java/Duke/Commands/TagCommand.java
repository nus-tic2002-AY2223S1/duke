package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;

public class TagCommand extends Command {
    private final int input;
    private final String tag;

    public TagCommand(int input, String tag) {
        this.input = input;
        this.tag = tag;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        Task currTask = taskList.getTask(input-1);
        currTask.addTagging(tag);
        return currTask.getDescription();
    }
}
