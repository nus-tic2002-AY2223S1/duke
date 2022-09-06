package service.command;

import form.Form;

/**
 * @description singleton class
 * handle `undefined command` operation
 * @author Dex
 * @date 2022/08/31
 */
public class UndefinedCommand extends Command {

    private static final UndefinedCommand command = new UndefinedCommand();

    private UndefinedCommand() {}

    public static UndefinedCommand getInstance() {
        return command;
    }

    /**
     * @description handle input which does not recognize by the program
     * @author Dex
     * @date 2022/09/06
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        String message = String.format("☹ OOPS!!! I'm sorry, but I don't know what [%s] means :-(", form.getMetaData());
        System.out.println(message);
    }
}
