package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.exception.EmptyTaskListException;
import duke.form.Form;
import duke.util.CollectionUtil;

import java.util.List;

/**
 * @description singleton class
 * perform `list task` operation
 * @author Dex
 * @date 2022/08/31
 */
public class ListTaskCommand extends Command {

    private static final ListTaskCommand command = new ListTaskCommand();

    private ListTaskCommand() {}

    public static ListTaskCommand getInstance() {
        return command;
    }

    /**
     * @description list all tasks which currently stored in the program
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public ResponseDto<List<Task>> execute(Form form) {
        List<Task> taskList = taskManager.getTaskList();
        if (CollectionUtil.isEmpty(taskList)) {
            throw new EmptyTaskListException();
        }

        return new ResponseDto<>(form.getCommand(), taskList);
    }
}
