package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Event;
import duke.form.EventForm;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * @description singleton class
 * perform `add event task` operation
 * @author Dex
 * @date 2022/08/31
 */
public class AddEventCommand extends Command {

    private static final AddEventCommand command = new AddEventCommand();

    private AddEventCommand() {}

    public static AddEventCommand getInstance() {
        return command;
    }

    /**
     * @description add event by given user input
     * @author Dex
     * @date 2022/09/02
     * @param form: parsed input form from user
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        EventForm eventForm = (EventForm) form;
        Event event = new Event(eventForm.getDescription(), eventForm.getStartTime(), eventForm.getEndTime());
        taskManager.addTask(event);
        AsyncExecutor.execute(() -> taskManager.persistTask());
        String message = String.format("Event [%s] is added!", eventForm.getDescription());
        return new ResponseDto<>(form.getCommand(), message);
    }
}
