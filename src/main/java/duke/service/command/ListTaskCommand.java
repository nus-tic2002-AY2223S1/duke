package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.exception.EmptyTaskListException;
import duke.form.Form;
import duke.util.CollectionUtil;

import java.util.List;

/**
 * Singleton class, perform `list task` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class ListTaskCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final ListTaskCommand command = new ListTaskCommand();

    private ListTaskCommand() {}

    /**
     * Get single instance.
     *
     * @return Single instance of command.
     */
    public static ListTaskCommand getInstance() {
        return command;
    }

    /**
     * List all tasks which currently stored in the program.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
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
