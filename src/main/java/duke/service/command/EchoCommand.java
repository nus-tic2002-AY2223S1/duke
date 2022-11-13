package duke.service.command;

import duke.dto.ResponseDto;
import duke.form.Form;

/**
 * Singleton class, perform `echo input` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class EchoCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final EchoCommand command = new EchoCommand();

    private EchoCommand() {}

    /**
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static EchoCommand getInstance() {
        return command;
    }

    /**
     * Prints input which user enters.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<String> execute(Form form) {
        ResponseDto<String> dto = new ResponseDto<>(form.getCommand());
        dto.setData(form.getMetaData());
        return dto;
    }
}
