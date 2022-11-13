package duke.service.command;

import duke.dto.ResponseDto;
import duke.form.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EchoCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        Command command = EchoCommand.getInstance();
        Form form = new Form(TEST_META_DATA_DESCRIPTION);
        ResponseDto<?> responseDto = command.execute(form);
        String data = (String) responseDto.getData();
        Assertions.assertEquals(TEST_META_DATA_DESCRIPTION, data);
    }
}
