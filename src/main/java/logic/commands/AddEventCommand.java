package logic.commands;

import common.exceptions.DuplicatedTaskException;
import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;
import model.Event;
import ui.ConsoleUi;

import static common.utils.StringUtil.getDescriptionFromString;
import static common.utils.StringUtil.getTimeFromString;
import static logic.validators.Validator.validateEvent;

public class AddEventCommand extends Command {
    public AddEventCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return adds event and prints event item added to taskList array
     *
     * @throws  DuplicatedTaskException
     * @throws  InvalidTaskDescriptionException
     */
    @Override
    public void execute() throws DuplicatedTaskException, InvalidTaskDescriptionException {
        String description = getDescriptionFromString(chat.getCommand(), chat.getInput());
        validateEvent(description, chat);

        Event newEvent = new Event(description, getTimeFromString(chat.getInput()));
        chat.getTaskList().add(newEvent);

        ui.printAddedDeletedTask(chat);
    }
}
