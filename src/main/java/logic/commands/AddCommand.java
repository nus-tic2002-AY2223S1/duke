package logic.commands;

import model.Chat;

import static common.constant.ErrorMessage.DUPLICATED_TASK_ERROR_MSG;
import static common.util.PrintUtil.printAddedTask;
import static common.util.PrintUtil.printLine;
import static common.util.StringUtil.getDescriptionFromString;
import static common.util.StringUtil.getFirstWord;
import static common.util.TaskUtil.checkDuplicatedTask;
import static logic.parser.AddCommandParser.parseAddCommand;

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

        if (checkDuplicatedTask(chat)) {
            System.out.println(String.format(DUPLICATED_TASK_ERROR_MSG, chat.getInput()));
        } else {
            String input = getDescriptionFromString(getFirstWord(chat.getInput()), chat.getInput());

            parseAddCommand(chat);
            printAddedTask(input, chat);
        }

        printLine();
    }
}
