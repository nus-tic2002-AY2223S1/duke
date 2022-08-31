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
            System.out.println("Current task list is empty! Please add in some tasks");
            return;
        }

        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d: %s%n", i+1, taskList.get(i).toString());
        }
    }
}
