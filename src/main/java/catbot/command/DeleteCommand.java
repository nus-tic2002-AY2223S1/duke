package catbot.command;

import catbot.*;
import catbot.task.Task;
import catbot.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    Integer taskNum;

    public DeleteCommand(Integer taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        Task task = t.getTask(taskNum);
        t.deleteTask(taskNum);
        ui.showMessage("Got it. Cat removed this task: \n" + task.getTaskName());
        ui.showMessage("Now human have " + t.getSize() + " tasks in the list");

        try{
            s.save(t);
        } catch (IOException ie) {
            System.out.println("Error saving");
        }
    }
}
