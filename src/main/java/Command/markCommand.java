package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class markCommand extends Command {
    private int index;

    public markCommand(int taskNumber){
        this.index = taskNumber-1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTasks(index).updateStatus(true);
        ui.marktaskAsDone();
        tasks.printTasks();
//        todo : add in storage function here
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
