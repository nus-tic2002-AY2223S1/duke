package Command;

import Entity.Task;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class unmarkCommand extends Command{
    private int index;

    public unmarkCommand(int taskNumber){
        this.index = taskNumber-1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks(index);
        task.updateStatus(false);
        ui.unmarkTask();
        ui.printTaskStatus(task);
        tasks.printTasks();
//        todo : add in storage function here
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
