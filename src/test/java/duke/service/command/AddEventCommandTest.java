package duke.service.command;

import duke.constant.CommandEnum;
import duke.entity.Event;
import duke.form.EventForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddEventCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        String commandName = CommandEnum.EVENT.getName();
        EventForm eventForm = new EventForm(TEST_META_DATA_DESCRIPTION, commandName, TEST_TASK_DESCRIPTION);
        eventForm.setStartTime(TEST_EVENT_START_TIME);
        eventForm.setEndTime(TEST_EVENT_END_TIME);

        Command command = AddEventCommand.getInstance();
        command.execute(eventForm);
        Event event = getNewlyAddedTask(Event.class);

        Event mock = new Event(TEST_TASK_DESCRIPTION);
        mock.setStartTime(TEST_EVENT_START_TIME);
        mock.setEndTime(TEST_EVENT_END_TIME);

        Assertions.assertEquals(mock.getType(), event.getType());
        Assertions.assertEquals(mock.getDescription(), event.getDescription());
        Assertions.assertEquals(mock.getStartTime(), event.getStartTime());
        Assertions.assertEquals(mock.getEndTime(), event.getEndTime());
        Assertions.assertEquals(mock.isDone(), event.isDone());
    }
}
