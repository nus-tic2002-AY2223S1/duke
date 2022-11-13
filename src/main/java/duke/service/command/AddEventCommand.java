package duke.service.command;

import duke.constant.Constant;
import duke.dto.ResponseDto;
import duke.entity.Event;
import duke.form.EventForm;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * Singleton class, perform `add event task` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class AddEventCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final AddEventCommand command = new AddEventCommand();

    private AddEventCommand() {}

    /**
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static AddEventCommand getInstance() {
        return command;
    }

    /**
     * Adds event by given user input.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        EventForm eventForm = (EventForm) form;
        Event event = new Event(eventForm.getDescription(), eventForm.getStartTime(), eventForm.getEndTime());
        taskManager.addTask(event);
        AsyncExecutor.execute(() -> taskManager.persistTask());
        String message = String.format(Constant.TASK_ADD_SUCCESS_MSG_TEMPLATE, event, taskManager.getTaskSize());
        return new ResponseDto<>(form.getCommand(), message);
    }
}
