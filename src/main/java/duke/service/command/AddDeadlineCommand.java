package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Deadline;
import duke.form.DeadlineForm;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * Singleton class, perform `add deadline task` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class AddDeadlineCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final AddDeadlineCommand command = new AddDeadlineCommand();

    private AddDeadlineCommand() {}

    /**
     * Get single instance.
     *
     * @return Single instance of command.
     */
    public static AddDeadlineCommand getInstance() {
        return command;
    }

    /**
     * Add deadline task by given user input.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        DeadlineForm deadlineForm = (DeadlineForm) form;
        Deadline deadline = new Deadline(deadlineForm.getDescription(), deadlineForm.getBy());
        taskManager.addTask(deadline);
        AsyncExecutor.execute(() -> taskManager.persistTask());
        String message = String.format("Deadline [%s] is added!", deadlineForm.getDescription());
        return new ResponseDto<>(form.getCommand(), message);
    }
}
