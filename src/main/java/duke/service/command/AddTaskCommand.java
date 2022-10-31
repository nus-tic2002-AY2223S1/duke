package duke.service.command;

import duke.constant.Constant;
import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * Singleton class, perform `add task`operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class AddTaskCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final AddTaskCommand command = new AddTaskCommand();

    private AddTaskCommand() {}

    /**
     * Get single instance.
     *
     * @return Single instance of command.
     */
    public static AddTaskCommand getInstance() {
        return command;
    }

    /**
     * Add task by given input form.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        Task task = new Task(form.getMetaData());
        taskManager.addTask(task);
        AsyncExecutor.execute(() -> taskManager.persistTask());
        String message = String.format(Constant.TASK_ADD_SUCCESS_MSG_TEMPLATE, task, taskManager.getTaskSize());
        return new ResponseDto<>(form.getCommand(), message);
    }
}
