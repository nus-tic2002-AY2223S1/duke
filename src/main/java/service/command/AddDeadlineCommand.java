package service.command;

import entity.Deadline;
import form.DeadlineForm;
import form.Form;

/**
 * @description singleton class
 * perform `add deadline task` operation
 * @author Dex
 * @date 2022/08/31
 */
public class AddDeadlineCommand extends Command {

    private static final AddDeadlineCommand command = new AddDeadlineCommand();

    private AddDeadlineCommand() {}

    public static AddDeadlineCommand getInstance() {
        return command;
    }

    /**
     * @description add deadline task by given user input
     * @author Dex
     * @date 2022/09/02
     * @param form: parsed input form from user
     */
    @Override
    public void execute(Form form) {
        DeadlineForm deadlineForm = (DeadlineForm) form;
        Deadline deadline = new Deadline(deadlineForm.getDescription(), deadlineForm.getBy());
        taskManager.addTask(deadline);
        System.out.printf("Deadline [%s] is added!%n", deadlineForm.getDescription());
    }
}
