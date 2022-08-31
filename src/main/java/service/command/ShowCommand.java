package service.command;

import constant.CommandEnum;
import entity.Form;

import java.util.Arrays;
import java.util.Objects;

public class ShowCommand extends Command {

    private static final ShowCommand command = new ShowCommand();

    private ShowCommand() {}

    public static ShowCommand getInstance() {
        return command;
    }

    @Override
    public void execute(Form form) {
        System.out.println("supported commands as follow:");
        Arrays.stream(CommandEnum.values())
                .filter(o -> !Objects.equals("unknown", o.getName()))
                .forEach(o -> System.out.println(o.getName()));
    }
}
