package duke.service.command;

import duke.dto.ResponseDto;
import duke.form.Form;

/**
 * Singleton class, handle `undefined command` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class UndefinedCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final UndefinedCommand command = new UndefinedCommand();

    private UndefinedCommand() {}

    /**
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static UndefinedCommand getInstance() {
        return command;
    }

    /**
     * Handles input which does not recognize by the program.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<Void> execute(Form form) {
        return new ResponseDto<>(form.getCommand(), String.format("☹ OOPS!!! I'm sorry, but I don't know what [%s] means :-(", form.getMetaData()));
    }
}
