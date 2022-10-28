package catbot.command;

import catbot.Storage;
import catbot.task.TaskList;
import catbot.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    Integer taskNum;

    public MarkCommand(Integer taskNum){
        this.taskNum = taskNum;
    }
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.markDone(taskNum);
        ui.showMessage("Good job human, Cat marked your task as done");
        ui.showMessage("[X] " + t.getTask(taskNum).getTaskName());

        try{
            s.save(t);
        } catch (IOException ie) {
            System.out.println("Error saving");
        }
    }
}
