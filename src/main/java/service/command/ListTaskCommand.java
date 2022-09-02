package service.command;

import entity.Task;
import entity.form.Form;
import util.CollectionUtil;

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
    public void execute(Form form) {
        List<Task> taskList = taskManager.getTaskList();
        if (CollectionUtil.isEmpty(taskList)) {
            System.out.println("current task list is empty! please add in some tasks");
            return;
        }

        for (int i = 0; i < taskList.size(); i++) {
            String taskInfo = String.format("%d: %s", i + 1, taskList.get(i).toString());
            System.out.println(taskInfo);
        }
    }
}
