package duke.service.command;

import duke.entity.Task;
import duke.form.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddTaskCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        Command command = AddTaskCommand.getInstance();
        Form form = new Form(TEST_META_DATA_DESCRIPTION);
        command.execute(form);

        Task task = getNewlyAddedTask(Task.class);
        Task mock = new Task(TEST_META_DATA_DESCRIPTION);

        Assertions.assertEquals(task.getDescription(), mock.getDescription());
        Assertions.assertEquals(task.getType(), mock.getType());
        Assertions.assertEquals(task.isDone(), mock.isDone());
    }
}
