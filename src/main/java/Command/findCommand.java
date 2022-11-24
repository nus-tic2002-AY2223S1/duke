package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class findCommand extends Command{
    private String searchKeyWord;

    public findCommand(String input){
        this.searchKeyWord = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFind();
        tasks.findTasks(searchKeyWord);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
