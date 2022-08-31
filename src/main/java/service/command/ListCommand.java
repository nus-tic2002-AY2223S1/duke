package service.command;

import entity.Form;
import entity.Task;

import java.util.List;

public class ListCommand extends Command {

    private static final ListCommand command = new ListCommand();

    private ListCommand() {}

    public static ListCommand getInstance() {
        return command;
    }

    @Override
    public void execute(Form form) {
        List<Task> taskList = taskManager.getTaskList();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d: %s%n", i+1, taskList.get(i).toString());
        }
    }
}
