package duke.service.command;

import duke.entity.Task;
import duke.form.Form;
import duke.pool.AsyncExecutor;

/**
 * @description singleton class
 * perform `add task`operation
 * @author Dex
 * @date 2022/08/31
 */
public class AddTaskCommand extends Command {

    private static final AddTaskCommand command = new AddTaskCommand();

    private AddTaskCommand() {}

    public static AddTaskCommand getInstance() {
        return command;
    }

    /**
     * @description add task by given input form
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
        AsyncExecutor.execute(() -> taskManager.persistTask());
    }
}
