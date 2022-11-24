package Command;

import Entity.Todo;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class todoCommand extends Command{
    private String todoDetails;
    public todoCommand(String taskdetails){
        this.todoDetails = taskdetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(todoDetails);
        tasks.addTask(new Todo(todoDetails));
        storage.persist(tasks);
//        System.out.println("execute addtask command");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
