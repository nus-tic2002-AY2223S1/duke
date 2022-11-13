package logic.commands;

import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;
import ui.ConsoleUi;

import static common.constants.CommonConstant.INPUT_OPTIONS;
import static logic.validators.Validator.validateHelp;

public class HelpCommand extends Command {
    public HelpCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return prints command usage
     *
     * @throws InvalidTaskDescriptionException
     */
    @Override
    public void execute() throws InvalidTaskDescriptionException {
        validateHelp(chat);

        System.out.println(INPUT_OPTIONS);
    }
}
