package service.command;

import constant.CommandEnum;
import entity.form.Form;

import java.util.Arrays;
import java.util.Objects;

/**
 * @description singleton class
 * perform `list command` operation
 * @author Dex
 * @date 2022/08/31
 */
public class ShowCommand extends Command {

    private static final ShowCommand command = new ShowCommand();

    private ShowCommand() {}

    public static ShowCommand getInstance() {
        return command;
    }


    /**
     * @description list all supported commands in the program
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        System.out.println("supported commands as follow:");
        Arrays.stream(CommandEnum.values())
                .filter(o -> !Objects.equals("unknown", o.getName()))
                .forEach(o -> System.out.println(o.getName()));
    }
}
