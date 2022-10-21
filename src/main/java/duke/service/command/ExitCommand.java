package duke.service.command;

import duke.constant.Constant;
import duke.dto.ResponseDto;
import duke.form.Form;

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
    public ResponseDto<Void> execute(Form form) {
        taskManager.persistTask();
        String message = String.format("%s%n%s", "It's been a pleasure to assist you, see you around!", Constant.ENDING_LOGO);
        return new ResponseDto<>(form.getCommand(), message);
    }
}
