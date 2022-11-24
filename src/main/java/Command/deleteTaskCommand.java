package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class deleteTaskCommand extends Command{
    private int taskNumber;

    public deleteTaskCommand(int taskNumner){
        this.taskNumber=taskNumner;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.deleteTask(taskNumber);
        storage.persist(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
