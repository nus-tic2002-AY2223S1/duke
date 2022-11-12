package Command;

import Entity.Deadline;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class deadlineCommand extends Command{
    private String detaildesp;
    private LocalDate by;

    public deadlineCommand(String desp,LocalDate by){
        this.detaildesp=desp;
        this.by=by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(detaildesp,by));
        storage.persist(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
