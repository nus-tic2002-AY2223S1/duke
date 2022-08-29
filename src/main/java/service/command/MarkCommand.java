package service.command;

import entity.Form;
import entity.Task;

import java.util.Iterator;
import java.util.List;

public class MarkCommand extends Command {

    private static final MarkCommand command = new MarkCommand();

    private MarkCommand() {}

    public static MarkCommand getInstance() {
        return command;
    }

    @Override
    public void execute(Form form) {
        System.out.println("mark action here ...");
    }
}
