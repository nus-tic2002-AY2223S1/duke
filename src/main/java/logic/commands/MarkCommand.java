package logic.commands;

import model.Chat;

import static common.constants.CommandConstant.MARK_COMMAND;
import static common.constants.CommonConstant.MARKED_TASK;
import static common.constants.ErrorMessage.MARKED_TASK_ERROR_MSG;
import static common.utils.PrintUtil.printCompletedTask;
import static common.utils.StringUtil.getDescriptionFromString;

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
        String input = getDescriptionFromString(MARK_COMMAND, chat.getInput());

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
    }
}
