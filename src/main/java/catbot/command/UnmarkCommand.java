package catbot.command;

import catbot.Storage;
import catbot.task.TaskList;
import catbot.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    Integer taskNum;

    public UnmarkCommand(Integer taskNum){
        this.taskNum = taskNum;
    }
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.unmarkDone(taskNum);
        ui.showMessage("Lmeow, Cat unmarked your task as done, pls make up your mind human");
        ui.showMessage("[ ] " + t.getTask(taskNum).getTaskName());

        try{
            s.save(t);
        } catch (IOException ie) {
            System.out.println("Error saving");
        }
    }
}
