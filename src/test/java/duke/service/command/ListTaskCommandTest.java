package duke.service.command;

import duke.form.Form;
import org.junit.jupiter.api.Test;

class ListTaskCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        // no examinable data set to verify, simply test execution function
        Command command = ListTaskCommand.getInstance();
        Form form = new Form(TEST_META_DATA_DESCRIPTION);
        command.execute(form);
    }
}
