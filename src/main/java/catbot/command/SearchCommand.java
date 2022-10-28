package catbot.command;

import catbot.Storage;
import catbot.task.Task;
import catbot.task.TaskList;
import catbot.Ui;

import java.util.ArrayList;

public class SearchCommand extends Command {
    String query;

    public SearchCommand(String query){
        this.query = query;
    }
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        TaskList results = t.searchTask(query);
        ui.showMessage(results.toString());
    }
}
