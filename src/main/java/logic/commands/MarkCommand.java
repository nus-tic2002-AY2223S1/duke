package logic.commands;

import model.Chat;

import static common.constant.CommonConstant.MARK_COMMAND;
import static common.constant.CommonConstant.MARKED_TASK;
import static common.constant.ErrorMessage.MARKED_TASK_ERROR_MSG;
import static common.constant.ErrorMessage.NOT_EXIST_TASK_ERROR_MSG;
import static common.util.PrintUtil.printFormatString;
import static common.util.PrintUtil.printLine;
import static common.util.StringUtil.getSubstringFromSentence;

public class MarkCommand extends Command {
    public MarkCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute marks a task
     *
     * @return {void}
     */
    @Override
    public void execute() {
        printLine();

        String input = getSubstringFromSentence(MARK_COMMAND, chat.getInput());

        if (chat.getTaskList().contains(input)) {
            printFormatString(NOT_EXIST_TASK_ERROR_MSG, input);
        }

        chat.getTaskList().forEach(task -> {
            if (!task.getDescription().equals(input)) {
                return;
            }

            if (task.getDone()) {
                printFormatString(MARKED_TASK_ERROR_MSG, input);
            } else {
                task.markDone();
                printFormatString(MARKED_TASK, input);
                System.out.println(task);
            }
        });

        printLine();
    }
}
