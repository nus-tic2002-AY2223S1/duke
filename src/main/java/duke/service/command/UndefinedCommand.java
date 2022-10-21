package duke.service.command;

import duke.dto.ResponseDto;
import duke.form.Form;

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
    public ResponseDto<Void> execute(Form form) {
        return new ResponseDto<>(form.getCommand(), String.format("☹ OOPS!!! I'm sorry, but I don't know what [%s] means :-(", form.getMetaData()));
    }
}
