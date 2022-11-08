package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.exception.EmptyTaskListException;
import duke.exception.IndexOutOfBoundsException;
import duke.form.DeleteForm;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * Singleton class, perform `delete task` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class DeleteTaskCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final DeleteTaskCommand command = new DeleteTaskCommand();

    private DeleteTaskCommand() {}

    /**
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static DeleteTaskCommand getInstance() {
        return command;
    }

    /**
     * Deletes task from list by given index.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
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
