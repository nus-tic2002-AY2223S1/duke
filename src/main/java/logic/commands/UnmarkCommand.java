package logic.commands;

import model.Chat;

import static common.constant.CommandConstant.UNMARK_COMMAND;
import static common.constant.CommonConstant.UNMARKED_TASK;
import static common.constant.ErrorMessage.NOT_EXIST_TASK_ERROR_MSG;
import static common.constant.ErrorMessage.UNMARKED_TASK_ERROR_MSG;
import static common.util.PrintUtil.printCompletedTask;
import static common.util.PrintUtil.printLine;
import static common.util.StringUtil.getDescriptionFromString;

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
        printLine();

        String input = getDescriptionFromString(UNMARK_COMMAND, chat.getInput());

        if (chat.getTaskList().contains(input)) {
            System.out.println(String.format(NOT_EXIST_TASK_ERROR_MSG, input));
        }

        chat.getTaskList().forEach(task -> {
            if (!task.getDescription().equals(input)) {
                return;
            }

            if (!task.getDone()) {
                System.out.println(String.format(UNMARKED_TASK_ERROR_MSG, input));
            } else {
                task.unmarkDone();
                printCompletedTask(UNMARKED_TASK, input, task);
            }
        });

        printLine();
    }
}
