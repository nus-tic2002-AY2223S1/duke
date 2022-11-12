package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;
import exception.CommandInvalidException;

public class markCommand extends Command {
    private int index;

    public markCommand(int taskNumber){
        this.index = taskNumber-1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int tasksize = tasks.getTaskCount();
        if(index+1>tasksize | index<0 ){
            throw new CommandInvalidException("please key in a valid task number,range:1-"+tasksize);
        } else{
            tasks.getTasks(index).updateStatus(true);
            ui.marktaskAsDone();
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
