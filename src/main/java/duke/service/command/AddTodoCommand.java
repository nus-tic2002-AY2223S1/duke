package duke.service.command;

import duke.constant.Constant;
import duke.dto.ResponseDto;
import duke.entity.Todo;
import duke.form.Form;
import duke.form.TodoForm;
import duke.pool.AsyncExecutor;

/**
 * Singleton class, perform `add todo task` operation
 *
 * @author Dex
 * @date 2022/08/31
 */
public class AddTodoCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final AddTodoCommand command = new AddTodoCommand();

    private AddTodoCommand() {}

    /**
     * Get single instance.
     *
     * @return Single instance of command.
     */
    public static AddTodoCommand getInstance() {
        return command;
    }

    /**
     * Add todo task by given user input.
     *
     * @param form: Parsed input form from user.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        TodoForm todoForm = (TodoForm) form;
        Todo todo = new Todo(todoForm.getDescription());
        taskManager.addTask(todo);
        AsyncExecutor.execute(() -> taskManager.persistTask());
        String message = String.format(Constant.TASK_ADD_SUCCESS_MSG_TEMPLATE, todo, taskManager.getTaskSize());
        return new ResponseDto<>(form.getCommand(), message);
    }
}
