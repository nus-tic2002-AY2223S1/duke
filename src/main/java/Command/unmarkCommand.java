package Command;

import Entity.Task;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;
import exception.CommandInvalidException;

public class unmarkCommand extends Command{
    private int index;

    public unmarkCommand(int taskNumber){
        this.index = taskNumber-1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int tasksize = tasks.getTaskCount();
        if(index+1>tasksize | index<0 ){
            String message = "please key in a valid task number,range:1-"+tasksize;
            throw new CommandInvalidException(message);
        } else{
        Task task = tasks.getTasks(index);
        task.updateStatus(false);
        ui.unmarkTask();
        System.out.printf("\t%d.",index+1);
        tasks.getTasks(index).print();
        storage.persist(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
