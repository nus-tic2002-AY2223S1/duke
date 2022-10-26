package duke.service.command;

import duke.dto.ResponseDto;
import duke.form.Form;
import duke.service.TaskManager;

/**
 * Base command class which acts as command class for all different command.
 *
 * @author Dex
 * @date 2022/08/31
 */
public abstract class Command {

    /**
     * Instance which provides operation on task, shared by all command instance.
     */
    protected static TaskManager taskManager = TaskManager.getInstance();

    /**
     * Description execute the different commands in runtime.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    public abstract ResponseDto<?> execute(Form form);
}
