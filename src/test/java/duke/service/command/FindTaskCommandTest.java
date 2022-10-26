package duke.service.command;

import duke.constant.CommandEnum;
import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.form.FindForm;
import duke.util.CollectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FindTaskCommandTest extends CommandTestBase {

    @Test
    @SuppressWarnings("unchecked")
    void testExecute() {
        // prefill the data
        Task taskAbc = new Task("abc");
        Task task1 = new Task("task1");
        Task task2 = new Task("task2");
        taskManager.addTask(taskAbc);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Command command = FindTaskCommand.getInstance();
        FindForm findForm = new FindForm(TEST_META_DATA_DESCRIPTION, CommandEnum.FIND_TASK.getName(), "task");
        ResponseDto<?> responseDto = command.execute(findForm);
        List<Task> list = (List<Task>) responseDto.getData();
        Assertions.assertEquals("task1", list.get(0).getDescription());
        Assertions.assertEquals("task2", list.get(1).getDescription());

        // cleanup
        taskManager.removeTask(0);
        taskManager.removeTask(0);
        taskManager.removeTask(0);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testExecuteWithEmptyResult() {
        Command command = FindTaskCommand.getInstance();
        FindForm findForm = new FindForm(TEST_META_DATA_DESCRIPTION, CommandEnum.FIND_TASK.getName(), "task");
        ResponseDto<?> responseDto = command.execute(findForm);
        List<Task> list = (List<Task>) responseDto.getData();
        Assertions.assertTrue(CollectionUtil.isEmpty(list));
    }
}
