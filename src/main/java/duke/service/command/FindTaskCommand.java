package duke.service.command;

import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.form.FindForm;
import duke.form.Form;
import duke.util.CollectionUtil;

import java.util.List;

/**
 * Singleton class, perform `find task` operation.
 *
 * @author Dex
 * @date 2022/08/31
 */
public class FindTaskCommand extends Command {

    /**
     * Variable holds the instance.
     */
    private static final FindTaskCommand command = new FindTaskCommand();

    private FindTaskCommand() {}

    /**
     * Returns single instance.
     *
     * @return Single instance of command.
     */
    public static FindTaskCommand getInstance() {
        return command;
    }

    /**
     * Finds the task from the given keyword description.
     *
     * @param form: Parsed input form from user.
     * @return Response entity after command execute.
     */
    @Override
    public ResponseDto<List<Task>> execute(Form form) {
        FindForm findForm = (FindForm) form;
        List<Task> list = taskManager.findTask(findForm.getKeyword());
        if (CollectionUtil.isEmpty(list)) {
            String message = String.format("☹ OOPS!!! could not find any task which has keyword [%s] in its description", findForm.getKeyword());
            return new ResponseDto<>(form.getCommand(), message);
        }

        return new ResponseDto<>(form.getCommand(), list);
    }
}
