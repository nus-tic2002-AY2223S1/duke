package duke.service.command;

import duke.constant.CommandEnum;
import duke.dto.CommandTableDto;
import duke.dto.ResponseDto;
import duke.form.Form;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Singleton class, perform `list command` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class ShowCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final ShowCommand command = new ShowCommand();

    private ShowCommand() {}

    /**
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static ShowCommand getInstance() {
        return command;
    }

    /**
     * Returns a table list which show the supported commands in the program.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<CommandTableDto> execute(Form form) {
        CommandTableDto commandTableDto = new CommandTableDto();
        List<List<String>> rows = Arrays.stream(CommandEnum.values())
                .filter(o -> !Objects.equals(o, CommandEnum.UNKNOWN))
                .map(o -> List.of(o.getName(), o.getDescription(), o.getSyntax()))
                .collect(Collectors.toList());
        commandTableDto.setHeaders(List.of("name", "description", "syntax"));
        commandTableDto.setRows(rows);
        return new ResponseDto<>(form.getCommand(), commandTableDto);
    }
}
