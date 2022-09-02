package service.command;

import entity.form.Form;

/**
 * @description singleton class
 * perform `exit program` operation
 * @author Dex
 * @date 2022/08/31
 */
public class ExitCommand extends Command {

    private static final ExitCommand command = new ExitCommand();

    private ExitCommand() {}

    public static ExitCommand getInstance() {
        return command;
    }

    /**
     * @description exit program, persist the task data
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        // System.out.println("exit action here ...");
        // taskManager.persistTask();
        System.out.println("It's been a pleasure to assist you, see you around!");
        System.exit(0);
    }
}
