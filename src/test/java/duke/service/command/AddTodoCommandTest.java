package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Todo;
import duke.form.TodoForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddTodoCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        String commandName = CommandEnum.TODO.getName();
        TodoForm todoForm = new TodoForm(TEST_META_DATA_DESCRIPTION, commandName, TEST_TASK_DESCRIPTION);

        Command command = AddTodoCommand.getInstance();
        command.execute(todoForm);
        Todo todo = getNewlyAddedTask(Todo.class);

        Todo mock = new Todo(TEST_TASK_DESCRIPTION);

        Assertions.assertEquals(mock.getType(), todo.getType());
        Assertions.assertEquals(mock.getDescription(), todo.getDescription());
        Assertions.assertEquals(mock.isDone(), todo.isDone());
    }
}
