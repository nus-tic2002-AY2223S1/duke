package logic.commands;

import model.Chat;
import model.Task;

import static common.constant.CommonConstant.ADDED;
import static common.constant.ErrorMessage.DUPLICATED_TASK_ERROR_MSG;
import static common.util.PrintUtil.printFormatString;
import static common.util.PrintUtil.printLine;
import static common.util.TaskUtil.checkDuplicatedTask;


public class AddCommand extends Command {
    public AddCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute adds and prints item added to taskList array
     *
     * @return {void}
     */
    @Override
    public void execute() {
        printLine();

        Task newTask = new Task(chat.getInput());
        if (checkDuplicatedTask(chat)) {
            printFormatString(DUPLICATED_TASK_ERROR_MSG, chat.getInput());
        } else {
            chat.getTaskList().add(newTask);
            printFormatString(ADDED, chat.getInput());
        }

        printLine();
    }
}
