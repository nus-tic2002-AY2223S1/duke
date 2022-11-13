package logic.commands;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import model.Chat;
import ui.ConsoleUi;

import static common.enums.CommandEnum.delete;
import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.utils.StringUtil.getDescriptionFromString;
import static logic.validators.Validator.validateDelete;

public class DeleteCommand extends Command {
    public DeleteCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return prints delete item from task list
     *
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     * @throws  NotExistTaskException
     */
    @Override
    public void execute() throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException {
        String description = getDescriptionFromString(delete, chat.getInput());
        validateDelete(description, chat);

        chat.getTaskList().remove(Integer.parseInt(description) - INIT_INT_VAL);
        ui.printAddedDeletedTask(chat);
    }
}
