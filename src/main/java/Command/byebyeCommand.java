package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class byebyeCommand extends Command{

    public byebyeCommand(){

    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\tBye~~ Hope to see you again soon!");
//        storage.deleteFile();
//        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
