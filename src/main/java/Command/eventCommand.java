package Command;

import Entity.Event;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;

public class eventCommand extends Command {
    private String detaildesp;
    private String at;

    public eventCommand(String detaildesp,String at){
        this.detaildesp=detaildesp;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.addTask(new Event(detaildesp,at));
        storage.persist(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
