package logic.commands;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import model.Chat;
import model.Task;
import ui.ConsoleUi;

import static common.enums.CommandEnum.mark;
import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.MARKED_TASK;
import static common.utils.StringUtil.getDescriptionFromString;
import static logic.validators.Validator.validateMark;

public class MarkCommand extends Command {
    public MarkCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return marks a task
     *
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     * @throws  NotExistTaskException
     * @throws  MarkedTaskException
     */
    @Override
    public void execute() throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException {
        String description = getDescriptionFromString(mark, chat.getInput());
        validateMark(description, chat);

        Task task = chat.getTaskList().get(Integer.parseInt(description) - INIT_INT_VAL);
        task.markDone();
        ui.printUpdatedMarkedTask(MARKED_TASK, description, task);
    }
}
