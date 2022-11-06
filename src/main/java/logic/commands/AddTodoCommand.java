package logic.commands;

import common.exceptions.DuplicatedTaskException;
import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;
import model.ToDo;
import ui.ConsoleUi;

import static common.utils.StringUtil.getDescriptionFromString;
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
        validateTodo(description, chat);

        ToDo newToDo = new ToDo(description);
        chat.getTaskList().add(newToDo);

        ui.printAddedDeletedTask(chat);
    }
}
