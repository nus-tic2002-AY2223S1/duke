package logic.commands;

import common.enums.PeriodicalEnum;
import common.exceptions.DuplicatedTaskException;
import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;
import model.ToDo;
import ui.ConsoleUi;

import static common.utils.StringUtil.getDescriptionFromString;
import static common.utils.StringUtil.getPeriodicalFromString;
import static logic.validators.Validator.validateTodo;

public class AddTodoCommand extends Command {
    public AddTodoCommand(ConsoleUi ui, Chat chat) {
        super(ui, chat);
    }

    /**
     * Return adds todo and prints todo item added to taskList array
     *
     * @throws  DuplicatedTaskException
     * @throws  InvalidTaskDescriptionException
     */
    @Override
    public void execute() throws DuplicatedTaskException, InvalidTaskDescriptionException {
        String description = getDescriptionFromString(chat.getCommand(), chat.getInput());
        PeriodicalEnum periodical = PeriodicalEnum.valueOf(getPeriodicalFromString(chat.getInput()));
        validateTodo(description, chat);

        ToDo newToDo = new ToDo(periodical, description);
        chat.getTaskList().add(newToDo);

        ui.printAddedDeletedTask(chat);
    }
}
