package duke.service.command;

import duke.constant.Constant;
import duke.dto.ResponseDto;
import duke.form.Form;

/**
 * Singleton class, perform `exit program` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class ExitCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final ExitCommand command = new ExitCommand();

    private ExitCommand() {}

    /**
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static ExitCommand getInstance() {
        return command;
    }

    /**
     * Exits program while persists the task data.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        taskManager.persistTask();
        String message = String.format("%s%n%s", "It's been a pleasure to assist you, see you around!", Constant.ENDING_LOGO);
        return new ResponseDto<>(form.getCommand(), message);
    }
}
