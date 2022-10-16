package logic.commands;

import model.Chat;
import model.Task;

import static common.constants.CommandConstant.UNMARK_COMMAND;
import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.UNMARKED_TASK;
import static common.constants.ErrorMessage.UNMARKED_TASK_ERROR_MSG;
import static common.utils.PrintUtil.printCompletedTask;
import static common.utils.StringUtil.getDescriptionFromString;

public class UnmarkCommand extends Command {
    public UnmarkCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute unmarks a task
     *
     * @return {void}
     */
    @Override
    public void execute() {
        String input = getDescriptionFromString(UNMARK_COMMAND, chat.getInput());
        Task task = chat.getTaskList().get(Integer.parseInt(input) - INIT_INT_VAL);

        if (!task.getDone()) {
            System.out.println(String.format(UNMARKED_TASK_ERROR_MSG, input));
        } else {
            task.unmarkDone();
            printCompletedTask(UNMARKED_TASK, input, task);
        }
    }
}
