package logic.commands;

import common.exceptions.DuplicatedTaskException;
import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;
import model.Deadline;
import ui.ConsoleUi;

import static common.utils.StringUtil.getDescriptionFromString;
import static common.utils.StringUtil.getTimeFromString;
import static logic.validators.Validator.validateDeadline;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return adds deadline and prints deadline item added to taskList array
     *
     * @throws  DuplicatedTaskException
     * @throws  InvalidTaskDescriptionException
     */
    @Override
    public void execute() throws DuplicatedTaskException, InvalidTaskDescriptionException {
        String description = getDescriptionFromString(chat.getCommand(), chat.getInput());
        validateDeadline(description, chat);

        Deadline newDeadline = new Deadline(description, getTimeFromString(chat.getInput()));
        chat.getTaskList().add(newDeadline);

        ui.printAddedDeletedTask(chat);
    }
}
