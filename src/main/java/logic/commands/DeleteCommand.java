package logic.commands;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import model.Chat;

import static common.constants.CommandConstant.DELETE_COMMAND;
import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.utils.PrintUtil.printAddedDeletedTask;
import static common.utils.StringUtil.getDescriptionFromString;
import static logic.validators.Validator.validateDelete;

public class DeleteCommand extends Command {
    public DeleteCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute prints delete item from task list
     *
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @throws NotExistTaskException
     * @return {void}
     */
    @Override
    public void execute() throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException {
        String description = getDescriptionFromString(DELETE_COMMAND, chat.getInput());
        validateDelete(description, chat);

        chat.getTaskList().remove(Integer.parseInt(description) - INIT_INT_VAL);
        printAddedDeletedTask(chat);
    }
}
