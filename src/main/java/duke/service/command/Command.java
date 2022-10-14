package duke.service.command;

import duke.form.Form;
import duke.service.TaskManager;

/**
 * @description base command class which acts as command class for all different command
 * @author Dex
 * @date 2022/08/31
 */
public abstract class Command {

    /**
     * instance which provides operation on task, shared by all command instance
     */
    protected static TaskManager taskManager = TaskManager.getInstance();

    /**
     * @description execute the different commands in runtime
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    public abstract void execute(Form form);
}
