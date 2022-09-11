package logic.commands;

import model.Chat;

import static common.constant.CommandConstant.MARK_COMMAND;
import static common.constant.CommonConstant.MARKED_TASK;
import static common.constant.ErrorMessage.NOT_EXIST_TASK_ERROR_MSG;
import static common.constant.ErrorMessage.MARKED_TASK_ERROR_MSG;
import static common.util.PrintUtil.printCompletedTask;
import static common.util.PrintUtil.printLine;
import static common.util.StringUtil.getDescriptionFromString;

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

        String input = getDescriptionFromString(MARK_COMMAND, chat.getInput());

        if (chat.getTaskList().contains(input)) {
            System.out.println(String.format(NOT_EXIST_TASK_ERROR_MSG, input));
        }

        chat.getTaskList().forEach(task -> {
            if (!task.getDescription().equals(input)) {
                return;
            }

            if (task.getDone()) {
                System.out.println(String.format(MARKED_TASK_ERROR_MSG, input));
            } else {
                task.markDone();
                printCompletedTask(MARKED_TASK, input, task);
            }
        });

        printLine();
    }
}
