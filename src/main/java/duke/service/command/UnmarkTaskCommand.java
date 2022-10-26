package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.exception.EmptyTaskListException;
import duke.exception.IndexOutOfBoundsException;
import duke.form.Form;
import duke.form.MarkingForm;
import duke.pool.AsyncExecutor;

/**
 * Singleton class, perform `mark task undone` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class UnmarkTaskCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final UnmarkTaskCommand command = new UnmarkTaskCommand();

    private UnmarkTaskCommand() {}

    /**
     * Get single instance.
     *
     * @return Single instance of command.
     */
    public static UnmarkTaskCommand getInstance() {
        return command;
    }

    /**
     * Mark task as `undone` status.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        MarkingForm markingForm = (MarkingForm) form;
        int taskSize = taskManager.getTaskSize();
        if (taskSize == 0) {
            throw new EmptyTaskListException();
        }

        int index = markingForm.getIndex();
        if (index > taskSize) {
            throw new IndexOutOfBoundsException();
        }

        // decrement for accessing correct index
        index--;
        Task task = taskManager.getTaskByIndex(index);
        task.setDone(false);
        AsyncExecutor.execute(() -> taskManager.persistTask());

        String message = String.format("%s%n%s", "OK, I've marked this task as not done yet:", task);
        return new ResponseDto<>(form.getCommand(), message);
    }
}
