package logic.commands;

import model.Chat;
import ui.ConsoleUi;

public class ByeCommand extends Command {
    public ByeCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return prints bye greeting
     */
    @Override
    public void execute() {
        ui.printBye();
        System.exit(0);
    }
}
