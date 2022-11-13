package duke.service.command;

import duke.form.Form;
import org.junit.jupiter.api.Test;

class ExitCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        // no examinable data set to verify, simply test execution function
        Command command = ExitCommand.getInstance();
        Form form = new Form(TEST_META_DATA_DESCRIPTION);
        command.execute(form);
        // delete the test-set file should be done manually
    }
}
