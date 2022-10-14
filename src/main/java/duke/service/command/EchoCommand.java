package duke.service.command;

import duke.form.Form;

/**
 * @description singleton class
 * perform `echo input` operation
 * @author Dex
 * @date 2022/08/31
 */
public class EchoCommand extends Command {

    private static final EchoCommand command = new EchoCommand();

    private EchoCommand() {}

    public static EchoCommand getInstance() {
        return command;
    }

    /**
     * @description echo input which user enters
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        System.out.println("echoing: " + form.getMetaData());
    }
}
