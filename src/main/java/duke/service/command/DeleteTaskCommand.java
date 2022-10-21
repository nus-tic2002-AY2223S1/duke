package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.exception.EmptyTaskListException;
import duke.exception.IndexOutOfBoundsException;
import duke.form.DeleteForm;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * @description singleton class
 * perform `delete task` operation
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
    public ResponseDto<Void> execute(Form form) {
        DeleteForm deleteForm = (DeleteForm) form;
        int taskSize = taskManager.getTaskSize();
        if (taskSize == 0) {
            throw new EmptyTaskListException();
        }

        int index = deleteForm.getIndex();
        if (index > taskSize) {
            throw new IndexOutOfBoundsException();
        }

        // decrement for accessing correct index
        index--;
        Task task = taskManager.getTaskByIndex(index);

        // remove task
        taskManager.removeTask(index);
        AsyncExecutor.execute(() -> taskManager.persistTask());

        String message = String.format("%s%n%s%n%s",
                "Noted. I've removed this task:",
                task.toString(),
                String.format("Now you have %d tasks in the list", taskManager.getTaskSize()));
        return new ResponseDto<>(form.getCommand(), message);
    }
}
