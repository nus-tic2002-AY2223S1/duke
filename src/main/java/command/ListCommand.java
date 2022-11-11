package command;

import ui.Ui;
import exceptions.DukeException;
import storage.Storage;


import task.TaskList;


public class ListCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage)throws DukeException {
        for(int i=0;i<tasks.tasks.size();i++){
            System.out.println((i+1)+"."+tasks.tasks.get(i).toString());

        }
    }

}
