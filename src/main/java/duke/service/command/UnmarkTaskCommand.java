package duke.service.command;

import duke.entity.Task;
import duke.exception.CommandArgsException;
import duke.form.Form;
import duke.form.MarkingForm;
import duke.pool.AsyncExecutor;

/**
 * @description singleton class
 * perform `mark task undone` operation
 * @author Dex
 * @date 2022/08/31
 */
public class UnmarkTaskCommand extends Command {

    private static final UnmarkTaskCommand command = new UnmarkTaskCommand();

    private UnmarkTaskCommand() {}

    public static UnmarkTaskCommand getInstance() {
        return command;
    }

    /**
     * @description mark task as `undone` status
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        MarkingForm markingForm = (MarkingForm) form;
        int taskSize = taskManager.getTaskSize();
        if (taskSize == 0) {
            System.out.println("empty task list! please add in some tasks first");
            return;
        }

        int index = markingForm.getIndex();
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
        AsyncExecutor.execute(() -> taskManager.persistTask());
    }
}
