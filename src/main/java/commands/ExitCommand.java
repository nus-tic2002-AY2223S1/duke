package commands;
import storage.Storage;
import taskList.TaskList;
import ui.UI;
public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    public void execute(TaskList task, UI ui, Storage storage) {
        UI.printBye();
    }

    @Override
    public boolean isExit(Command command) {
        return true; // instanceof returns false if it is null
    }
}
