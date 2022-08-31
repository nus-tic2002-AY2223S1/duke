package service.command;

import entity.Form;

public class EchoCommand extends Command {

    private static final EchoCommand command = new EchoCommand();

    private EchoCommand() {}

    public static EchoCommand getInstance() {
        return command;
    }

    /**
     * @description level 1 implementation, echo the content which user inputs
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        System.out.println("echoing: " + form.getMetaData());
    }
}
