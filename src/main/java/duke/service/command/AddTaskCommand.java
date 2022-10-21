package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * @description singleton class
 * perform `add task`operation
 * @author Dex
 * @date 2022/08/31
 */
public class AddTaskCommand extends Command {

    private static final AddTaskCommand command = new AddTaskCommand();

    private AddTaskCommand() {}

    public static AddTaskCommand getInstance() {
        return command;
    }

    /**
     * @description add task by given input form
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        Task task = new Task(form.getMetaData());
        taskManager.addTask(task);
        AsyncExecutor.execute(() -> taskManager.persistTask());
        String message = String.format("Task [%s] is added!", form.getMetaData());
        return new ResponseDto<>(form.getCommand(), message);
    }
}
