package logic.commands;

import common.exceptions.DuplicatedTaskException;
import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;
import model.ToDo;

import static common.utils.PrintUtil.printAddedDeletedTask;
import static common.utils.StringUtil.getDescriptionFromString;
import static logic.validators.Validator.validateTodo;

public class AddTodoCommand extends Command {
    public AddTodoCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute adds todo and prints todo item added to taskList array
     *
     * @return {void}
     */
    @Override
    public void execute() throws DuplicatedTaskException, InvalidTaskDescriptionException {
        String description = getDescriptionFromString(chat.getCommand(), chat.getInput());
        validateTodo(description, chat);

        ToDo newToDo = new ToDo(description);
        chat.getTaskList().add(newToDo);

        printAddedDeletedTask(chat);
    }
}
