package service.command;

import entity.Form;

public class UnmarkCommand extends Command {

    private static final UnmarkCommand command = new UnmarkCommand();

    private UnmarkCommand() {}

    public static UnmarkCommand getInstance() {
        return command;
    }

    @Override
    public void execute(Form form) {
        System.out.println("unmark action here ...");
    }
}
