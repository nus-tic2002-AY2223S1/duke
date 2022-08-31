package service.command;

import entity.Form;
import entity.Task;
import util.CollectionUtil;

import java.util.List;

public class ListTaskCommand extends Command {

    private static final ListTaskCommand command = new ListTaskCommand();

    private ListTaskCommand() {}

    public static ListTaskCommand getInstance() {
        return command;
    }

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
