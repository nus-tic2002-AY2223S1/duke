package service.command;

import entity.Todo;
import form.Form;
import form.TodoForm;

/**
 * @description singleton class
 * perform `echo input` operation
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
    public void execute(Form form) {
        TodoForm todoForm = (TodoForm) form;
        Todo todo = new Todo(todoForm.getDescription());
        taskManager.addTask(todo);
        System.out.println("Todo [%s] is added!%n");
    }
}
