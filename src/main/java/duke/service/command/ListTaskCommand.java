package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
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
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static ListTaskCommand getInstance() {
        return command;
    }

    /**
     * Returns a list tasks which currently stored in the program.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<List<Task>> execute(Form form) {
        List<Task> taskList = taskManager.getTaskList();
        if (CollectionUtil.isEmpty(taskList)) {
            String message = "current task list is empty, kindly feed me some tasks before perform any operation";
            return new ResponseDto<>(form.getCommand(), message);
        }
        return new ResponseDto<>(form.getCommand(), taskList);
    }
}
