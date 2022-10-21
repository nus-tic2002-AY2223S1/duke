package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.exception.EmptyTaskListException;
import duke.exception.IndexOutOfBoundsException;
import duke.form.Form;
import duke.form.MarkingForm;
import duke.pool.AsyncExecutor;

/**
 * @description singleton class
 * perform `mark task as done` operation
 * @author Dex
 * @date 2022/08/31
 */
public class MarkTaskCommand extends Command {

    private static final MarkTaskCommand command = new MarkTaskCommand();

    private MarkTaskCommand() {}

    public static MarkTaskCommand getInstance() {
        return command;
    }

    /**
     * @description mark task as `done` status
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
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
        task.setDone(true);
        AsyncExecutor.execute(() -> taskManager.persistTask());

        String message = String.format("%s%n%s", "Nice! I've marked this task as done", task);
        return new ResponseDto<>(form.getCommand(), message);
    }
}
