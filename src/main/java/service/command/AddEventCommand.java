package service.command;

import entity.Event;
import form.EventForm;
import form.Form;

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
    public void execute(Form form) {
        EventForm eventForm = (EventForm) form;
        Event event = new Event(eventForm.getDescription(), eventForm.getStartTime(), eventForm.getEndTime());
        taskManager.addTask(event);
        System.out.printf("Event [%s] is added!%n", eventForm.getDescription());
    }
}
