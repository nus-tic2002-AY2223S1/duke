package logic.commands;

import model.Chat;

import static common.constants.CommandConstant.DELETE_COMMAND;
import static common.constants.CommonConstant.*;
import static common.utils.PrintUtil.printAddedTask;
import static common.utils.StringUtil.getDescriptionFromString;

public class DeleteCommand extends Command {
    public DeleteCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute prints delete item from task list
     *
     * @return {void}
     */
    @Override
    public void execute() {
        String input = getDescriptionFromString(DELETE_COMMAND, chat.getInput());
        chat.getTaskList().remove(Integer.parseInt(input) - INIT_INT_VAL);
        printAddedTask(chat);
    }
}
