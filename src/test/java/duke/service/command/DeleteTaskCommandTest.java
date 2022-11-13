package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Todo;
import duke.form.DeleteForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeleteTaskCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        // prefill the data set
        Todo e = new Todo(TEST_TASK_DESCRIPTION);
        taskManager.addTask(e);
        int previousSize = taskManager.getTaskSize();

        String commandName = CommandEnum.DELETE_TASK.getName();
        DeleteForm deleteForm = new DeleteForm(TEST_META_DATA_DESCRIPTION, commandName, getUserInputLastIndex());

        Command command = DeleteTaskCommand.getInstance();
        command.execute(deleteForm);

        Assertions.assertEquals(previousSize - 1, taskManager.getTaskSize());
    }
}
