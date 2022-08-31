package service.command;

import entity.Form;
import entity.Task;

public class AddCommand extends Command {

    private static final AddCommand command = new AddCommand();

    private AddCommand() {}

    public static AddCommand getInstance() {
        return command;
    }

    /**
     * @description level 2 implementation, add task
     * @author Dex
     * @date 2022/08/31
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        Task task = new Task(form.getMetaData());
        taskManager.addTask(task);
        System.out.println("Process to add a new task ...");
        System.out.printf("Task [%s] is added!%n", form.getMetaData());
    }
}
