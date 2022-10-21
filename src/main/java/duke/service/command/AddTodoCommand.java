package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Todo;
import duke.form.Form;
import duke.form.TodoForm;
import duke.pool.AsyncExecutor;

/**
 * @description singleton class
 * perform `add todo task` operation
 * @author Dex
 * @date 2022/08/31
 */
public class AddTodoCommand extends Command {

    private static final AddTodoCommand command = new AddTodoCommand();

    private AddTodoCommand() {}

    public static AddTodoCommand getInstance() {
        return command;
    }

    /**
     * @description add todo task by given user input
     * @author Dex
     * @date 2022/09/02
     * @param form: parsed input form from user
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        TodoForm todoForm = (TodoForm) form;
        Todo todo = new Todo(todoForm.getDescription());
        taskManager.addTask(todo);
        AsyncExecutor.execute(() -> taskManager.persistTask());
        String message = String.format("Todo [%s] is added!", todoForm.getDescription());
        return new ResponseDto<>(form.getCommand(), message);
    }
}
