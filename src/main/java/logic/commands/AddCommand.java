package logic.commands;

import model.Chat;

import static common.utils.PrintUtil.printAddedTask;
import static common.utils.StringUtil.getDescriptionFromString;
import static logic.parsers.AddCommandParser.parseAddCommand;

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
        String description = getDescriptionFromString(chat.getCommand(), chat.getInput());

        parseAddCommand(description, chat);
        printAddedTask(chat);
    }
}
