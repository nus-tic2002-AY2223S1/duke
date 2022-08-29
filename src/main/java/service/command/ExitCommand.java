package service.command;

import entity.Form;

public class ExitCommand extends Command {

    private static final ExitCommand command = new ExitCommand();

    private ExitCommand() {}

    public static ExitCommand getInstance() {
        return command;
    }

    @Override
    public void execute(Form form) {
        System.out.println("exit action here ...");
        taskManager.persistTask();
        System.exit(0);
    }
}
