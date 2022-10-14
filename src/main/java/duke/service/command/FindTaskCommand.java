package duke.service.command;

import duke.entity.Task;
import duke.form.FindForm;
import duke.form.Form;
import duke.util.CollectionUtil;

import java.util.List;

/**
 * @description singleton class
 * perform `find task` operation
 * @author Dex
 * @date 2022/08/31
 */
public class FindTaskCommand extends Command {

    private static final FindTaskCommand command = new FindTaskCommand();

    private FindTaskCommand() {}

    public static FindTaskCommand getInstance() {
        return command;
    }

    @Override
    public void execute(Form form) {
        System.out.println("performing search ...");

        FindForm findForm = (FindForm) form;
        List<Task> list = taskManager.findTask(findForm.getKeyword());
        if (CollectionUtil.isEmpty(list)) {
            System.out.printf("☹ OOPS!!! could not find any task which has keyword [%s] in its description%n", findForm.getKeyword());
            return;
        }

        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : list) {
            System.out.printf("%d. %s%n", index++, task.toString());
        }
    }
}
