package logic.commands;

import common.enums.UpdateTypeEnum;
import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.UnmarkedTaskException;
import model.Chat;
import model.Task;
import ui.ConsoleUi;

import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.UPDATED_TASK;
import static common.enums.CommandEnum.update;
import static common.utils.StringUtil.getDescriptionFromString;
import static common.utils.StringUtil.getUpdateIdFromString;
import static common.utils.StringUtil.getUpdateEnumTypeFromString;
import static common.utils.StringUtil.getUpdateDescriptionFromString;
import static logic.validators.Validator.validateUpdate;

public class UpdateCommand extends Command {
    public UpdateCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return handle update type: desc, date such as setting description and time respectively
     *
     * @param   updateType
     * @param   description   description of user input
     * @param   task
     */
    public static void updateTypeHandler(UpdateTypeEnum updateType, String description, Task task) {
        switch (updateType) {
            case desc:
                task.setDescription(description);
                break;
            case date:
                task.setTime(description);
                break;
            default:
                break;
        }
    }

    /**
     * Return update a task
     *
     * @throws  EmptyTaskListException
     * @throws  UnmarkedTaskException
     * @throws  InvalidTaskDescriptionException
     * @throws  NotExistTaskException
     */
    @Override
    public void execute() throws EmptyTaskListException, UnmarkedTaskException, InvalidTaskDescriptionException, NotExistTaskException {
        String description = getDescriptionFromString(update, chat.getInput());
        validateUpdate(getUpdateEnumTypeFromString(description), getUpdateIdFromString(description), chat);

        Task task = chat.getTaskList().get(Integer.parseInt(getUpdateIdFromString(description)) - INIT_INT_VAL);
        updateTypeHandler(getUpdateEnumTypeFromString(description), getUpdateDescriptionFromString(description), task);

        ui.printUpdatedMarkedTask(UPDATED_TASK, getUpdateDescriptionFromString(description), task);
    }
}
