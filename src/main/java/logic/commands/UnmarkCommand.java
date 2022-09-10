package logic.commands;

import model.Chat;

import static common.constant.CommonConstant.UNMARK_COMMAND;
import static common.constant.CommonConstant.UNMARKED_TASK;
import static common.constant.ErrorMessage.NOT_EXIST_TASK_ERROR_MSG;
import static common.constant.ErrorMessage.UNMARKED_TASK_ERROR_MSG;
import static common.util.PrintUtil.printFormatString;
import static common.util.PrintUtil.printLine;
import static common.util.StringUtil.getSubstringFromSentence;

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

        String input = getSubstringFromSentence(UNMARK_COMMAND, chat.getInput());
        chat.getTaskList().forEach(task -> {
            if (!task.getDescription().equals(input)) {
                printFormatString(NOT_EXIST_TASK_ERROR_MSG, input);
                return;
            }

            if (!task.getDone()) {
                printFormatString(UNMARKED_TASK_ERROR_MSG, input);
            } else {
                task.unmarkDone();
                printFormatString(UNMARKED_TASK, input);
                System.out.println(task);
            }
        });

        printLine();
    }
}
