package logic.commands;

import common.exceptions.*;
import model.Chat;
import model.Task;

import static common.constants.CommandConstant.MARK_COMMAND;
import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.MARKED_TASK;
import static common.utils.PrintUtil.printMarkedTask;
import static common.utils.StringUtil.getDescriptionFromString;
import static logic.parsers.TaskValidationParser.validateMark;

public class MarkCommand extends Command {
    public MarkCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute marks a task
     *
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @throws NotExistTaskException
     * @throws MarkedTaskException
     * @return {void}
     */
    @Override
    public void execute() throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException {
        String description = getDescriptionFromString(MARK_COMMAND, chat.getInput());
        validateMark(description, chat);

        Task task = chat.getTaskList().get(Integer.parseInt(description) - INIT_INT_VAL);
        task.markDone();
        printMarkedTask(MARKED_TASK, description, task);
    }
}
