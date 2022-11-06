package logic.commands;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import model.Chat;
import model.Task;
import ui.ConsoleUi;

import static common.enums.CommandEnum.find;
import static common.utils.StringUtil.getDescriptionFromString;
import static logic.validators.Validator.validateFind;

public class FindCommand extends Command {
    public FindCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return prints command usage
     *
     * @throws InvalidTaskDescriptionException
     */
    @Override
    public void execute() throws InvalidTaskDescriptionException, EmptyTaskListException, NotExistTaskException {
        String description = getDescriptionFromString(find, chat.getInput());
        validateFind(chat);

        ui.printMatchingTaskList();
        int i = 1;
        for (Task task : chat.getTaskList()) {
            if (task.getDescription().contains(description)) {
                System.out.println(i + ". " + task);
            }
            i++;
        }
    }
}
