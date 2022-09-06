package service.command;

import entity.Task;
import exception.CommandArgsException;
import form.DeleteForm;
import form.Form;

/**
 * @description singleton class
 * perform `echo input` operation
 * @author Dex
 * @date 2022/08/31
 */
public class DeleteTaskCommand extends Command {

    private static final DeleteTaskCommand command = new DeleteTaskCommand();

    private DeleteTaskCommand() {}

    public static DeleteTaskCommand getInstance() {
        return command;
    }

    /**
     * @description delete task from list by given index
     * @author Dex
     * @date 2022/09/06
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        DeleteForm deleteForm = (DeleteForm) form;
        int taskSize = taskManager.getTaskSize();
        if (taskSize == 0) {
            System.out.println("empty task list! please add in some tasks first");
            return;
        }

        int index = deleteForm.getIndex();
        if (index > taskSize) {
            throw new CommandArgsException("given index is invalid, it should be less than current task size");
        }

        // decrement for accessing correct index
        index--;
        Task task = taskManager.getTaskByIndex(index);

        // remove task
        taskManager.removeTask(index);

        // print message
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list%n", taskManager.getTaskSize());
    }
}
