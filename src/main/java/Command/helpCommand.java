package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class helpCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\tI. to record ur todo/important deadlines/appointment");
        System.out.println("\t 1)todo xxx e.g. todo borrow book");
        System.out.println("\t 2)deadline xxx /by xxx e.g. deadline return book /by YYYY-MM-DD");
        System.out.println("\t 3)event xxx /at xxxx e.g.event project meeting /at YYYY-MM-DD 2-4pm\n");

        System.out.println("\tII. to check ur list");
        System.out.println("\t 1)You could key \"list\" to get the full list of your plans\n");

        System.out.println("\tIII. to update ur list");
        System.out.println("\t 1)you could update the status of your tasks by key \"mark/unmark X\": e.g. mark 1");
        System.out.println("\t 2)you could delete the task by key \"delete X\": e.g. delete 2");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
