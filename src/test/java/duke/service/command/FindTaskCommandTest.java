package duke.service.command;

import duke.constant.CommandEnum;
import duke.form.FindForm;
import org.junit.jupiter.api.Test;

class FindTaskCommandTest extends CommandTestBase {

    @Test
    void testExecute() {
        // no examinable data set to verify, simply test execution function
        Command command = FindTaskCommand.getInstance();
        FindForm findForm = new FindForm(TEST_META_DATA_DESCRIPTION, CommandEnum.FIND_TASK.getName(), "");
        command.execute(findForm);
    }
}
