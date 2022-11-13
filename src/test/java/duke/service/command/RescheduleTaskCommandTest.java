package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Deadline;
import duke.entity.Task;
import duke.exception.DukeException;
import duke.form.RescheduleForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RescheduleTaskCommandTest extends CommandTestBase {

    @Test
    void testExecuteWithException() {
        // prefill data
        Task task1 = new Task("task1");
        taskManager.addTask(task1);

        Command command = RescheduleTaskCommand.getInstance();
        String commandName = CommandEnum.RESCHEDULE.getName();
        RescheduleForm rescheduleForm = new RescheduleForm(TEST_META_DATA_DESCRIPTION, commandName, getUserInputLastIndex());
        try {
            command.execute(rescheduleForm);
        } catch (DukeException e) {
            Assertions.assertEquals("error code: 603, message: selected task is not `event` or `deadline` task, cannot be rescheduled", e.getMessage());
        } finally {
            taskManager.removeTask(0);
        }
    }
}
