package command;

import exceptions.DukeException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class Command{
    protected boolean toExit=false;



    public Boolean isExit(){

        return toExit;

    }

    public void execute(TaskList tasks, Ui ui, Storage storage)throws DukeException {

    }

}

