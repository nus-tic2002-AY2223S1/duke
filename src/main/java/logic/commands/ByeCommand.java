package logic.commands;

import model.Chat;

import static common.utils.PrintUtil.printBye;

public class ByeCommand extends Command {
    public ByeCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute prints bye greeting
     *
     * @return {void}
     */
    @Override
    public void execute() {
        printBye();
        System.exit(0);
    }
}
