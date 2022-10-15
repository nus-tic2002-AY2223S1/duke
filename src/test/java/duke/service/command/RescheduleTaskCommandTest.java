package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Deadline;
import duke.form.RescheduleForm;
import org.junit.jupiter.api.Test;

class RescheduleTaskCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        // prefill the data set
        Deadline e = new Deadline(TEST_TASK_DESCRIPTION);
        e.setBy(TEST_DEADLINE_BY_TIME);
        taskManager.addTask(e);

        String commandName = CommandEnum.RESCHEDULE.getName();
        RescheduleForm rescheduleForm = new RescheduleForm(TEST_META_DATA_DESCRIPTION, commandName, getUserInputLastIndex());

        // as input is fetched in console, need to change the way how scanner takes in input
        // no testable at the moment

        // Command command = RescheduleTaskCommand.getInstance();
        // command.execute(rescheduleForm);
        // Deadline deadline = getNewlyAddedTask(Deadline.class);
        //
        // // mock deadline time should be matched with time which user inputs
        // Deadline mock = new Deadline(TEST_TASK_DESCRIPTION);
        // mock.setBy(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        //
        // Assertions.assertEquals(mock.getType(), deadline.getType());
        // Assertions.assertEquals(mock.getDescription(), deadline.getDescription());
        // Assertions.assertEquals(mock.getBy(), deadline.getBy());
        // Assertions.assertEquals(mock.isDone(), deadline.isDone());
    }
}
