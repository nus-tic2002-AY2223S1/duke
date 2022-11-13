package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Todo;
import duke.form.MarkingForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MarkTaskCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        // prefill the data set
        Todo e = new Todo(TEST_TASK_DESCRIPTION);
        taskManager.addTask(e);

        String commandName = CommandEnum.MARK_TASK.getName();
        MarkingForm markingForm = new MarkingForm(TEST_META_DATA_DESCRIPTION, commandName, getUserInputLastIndex());

        Command command = MarkTaskCommand.getInstance();
        command.execute(markingForm);
        Todo todo = getNewlyAddedTask(Todo.class);

        Assertions.assertTrue(todo.isDone());
    }
}
