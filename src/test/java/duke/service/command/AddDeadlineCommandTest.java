package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Deadline;
import duke.form.DeadlineForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddDeadlineCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        String commandName = CommandEnum.DEADLINE.getName();
        DeadlineForm deadlineForm = new DeadlineForm(TEST_META_DATA_DESCRIPTION, commandName, TEST_TASK_DESCRIPTION);
        deadlineForm.setBy(TEST_DEADLINE_BY_TIME);

        Command command = AddDeadlineCommand.getInstance();
        command.execute(deadlineForm);
        Deadline deadline = getNewlyAddedTask(Deadline.class);

        Deadline mock = new Deadline(TEST_TASK_DESCRIPTION);
        mock.setBy(TEST_DEADLINE_BY_TIME);

        Assertions.assertEquals(mock.getType(), deadline.getType());
        Assertions.assertEquals(mock.getDescription(), deadline.getDescription());
        Assertions.assertEquals(mock.getBy(), deadline.getBy());
        Assertions.assertEquals(mock.isDone(), deadline.isDone());
    }
}
