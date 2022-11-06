package Command;

import Storage.*;
import Storage.TaskList;
import Ui.Ui;

public class listCommand extends Command{
//    private void execute(){
//        TaskList.printTasks();
//    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }
}
