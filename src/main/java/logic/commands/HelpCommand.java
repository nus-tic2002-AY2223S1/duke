package logic.commands;

import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;

import static common.constants.CommonConstant.INPUT_OPTIONS;
import static logic.validators.Validator.validateHelp;

public class HelpCommand extends Command {
    public HelpCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute prints command usage
     *
     * @throws InvalidTaskDescriptionException
     * @return {void}
     */
    @Override
    public void execute() throws InvalidTaskDescriptionException {
        validateHelp(chat);

        System.out.println(INPUT_OPTIONS);
    }
}
