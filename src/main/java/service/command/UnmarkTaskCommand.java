package service.command;

import entity.Form;
import entity.Task;
import exception.CommandArgsException;

public class UnmarkTaskCommand extends Command {

    private static final UnmarkTaskCommand command = new UnmarkTaskCommand();

    private UnmarkTaskCommand() {}

    public static UnmarkTaskCommand getInstance() {
        return command;
    }

    @Override
    public void execute(Form form) {
        int taskSize = taskManager.getTaskSize();
        if (taskSize == 0) {
            System.out.println("empty task list! please add in some tasks first");
            return;
        }

        int index = form.getOperateIndex();
        if (index > taskSize) {
            throw new CommandArgsException("given index is invalid, it should be less than current task size");
        }

        // decrement for accessing correct index
        index--;
        Task task = taskManager.getTaskByIndex(index);
        task.setDone(false);

        // print message
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
}
