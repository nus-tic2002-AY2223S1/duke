package duke.service.command;

import duke.constant.CommandEnum;
import duke.dto.CommandTableDto;
import duke.dto.ResponseDto;
import duke.form.Form;
import duke.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class ShowCommandTest extends CommandTestBase {

    @Test
    @SuppressWarnings("unchecked")
    void testExecute() {
        Command command = ShowCommand.getInstance();
        Form form = new Form(TEST_META_DATA_DESCRIPTION);
        ResponseDto<CommandTableDto> responseDto = (ResponseDto<CommandTableDto>) command.execute(form);
        CommandTableDto data = responseDto.getData();

        List<String> headers = data.getHeaders();
        Assertions.assertEquals(List.of("name", "description", "syntax"), headers);

        List<List<String>> rows = Arrays.stream(CommandEnum.values())
                .filter(o -> !Objects.equals(o, CommandEnum.UNKNOWN))
                .map(o -> List.of(o.getName(), o.getDescription(), o.getSyntax()))
                .collect(Collectors.toList());
        Assertions.assertEquals(rows, data.getRows());
    }
}
