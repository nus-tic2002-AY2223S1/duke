package duke.service.command;

import duke.constant.CommandEnum;
import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.exception.DukeException;
import duke.form.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ListTaskCommandTest extends CommandTestBase {

    @Test
    @SuppressWarnings("unchecked")
    void testExecute() {
        // prefill the data
        Task task1 = new Task("task1");
        Task task2 = new Task("task2");
        Task task3 = new Task("task3");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        Command command = ListTaskCommand.getInstance();
        Form form = new Form(TEST_META_DATA_DESCRIPTION, CommandEnum.LIST.getName());
        ResponseDto<?> responseDto = command.execute(form);
        List<Task> list = (List<Task>) responseDto.getData();
        Assertions.assertEquals("task1", list.get(0).getDescription());
        Assertions.assertEquals("task2", list.get(1).getDescription());
        Assertions.assertEquals("task3", list.get(2).getDescription());

        // cleanup
        taskManager.removeTask(0);
        taskManager.removeTask(0);
        taskManager.removeTask(0);
    }

    @Test
    void testExecuteWithEmptyResult() {
        Command command = ListTaskCommand.getInstance();
        Form form = new Form(TEST_META_DATA_DESCRIPTION, CommandEnum.LIST.getName());
        try {
            command.execute(form);
        } catch (DukeException e) {
            Assertions.assertEquals("error code: 602, message: empty task list! please add in some tasks first", e.getMessage());
        }
    }
}
