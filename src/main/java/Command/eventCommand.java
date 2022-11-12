package Command;

import Entity.Event;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class eventCommand extends Command {
    private String detaildesp;
    private LocalDate date;
    private String at;

    public eventCommand(String detaildesp,LocalDate date,String at){
        this.detaildesp=detaildesp;
        this.date = date;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.addTask(new Event(detaildesp,date,at));
        storage.persist(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
