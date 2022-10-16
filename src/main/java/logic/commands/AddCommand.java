package logic.commands;

import common.exceptions.DuplicatedTaskException;
import common.exceptions.InvalidTaskDescriptionException;
import model.Chat;
import model.Deadline;
import model.Event;
import model.ToDo;

import static common.constants.CommandConstant.ADD_DEADLINE_COMMAND;
import static common.constants.CommandConstant.ADD_EVENT_COMMAND;
import static common.constants.CommandConstant.ADD_TODO_COMMAND;
import static common.constants.ErrorMessage.DUPLICATED_TASK_ERROR_MSG;
import static common.constants.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.constants.ErrorMessage.INVALID_TASK_DESCRIPTION_ERROR_MSG;
import static common.utils.PrintUtil.printAddedDeletedTask;
import static common.utils.StringUtil.getDescriptionFromString;
import static common.utils.StringUtil.getFirstWord;
import static common.utils.StringUtil.getTimeFromString;
import static logic.parsers.TaskValidationParser.validateDeadline;
import static logic.parsers.TaskValidationParser.validateEvent;
import static logic.parsers.TaskValidationParser.validateTodo;

public class AddCommand extends Command {
    public AddCommand(Chat chat) {
        super(chat);
    }

    /**
     * execute adds and prints item added to taskList array
     *
     * @return {void}
     */
    @Override
    public void execute() {
        String description = getDescriptionFromString(chat.getCommand(), chat.getInput());

        try {
            switch (getFirstWord(chat.getInput())) {
                case ADD_DEADLINE_COMMAND:
                    validateDeadline(description, chat);
                    Deadline newDeadline = new Deadline(description, getTimeFromString(chat.getInput()));
                    chat.getTaskList().add(newDeadline);
                    break;
                case ADD_EVENT_COMMAND:
                    validateEvent(description, chat);
                    Event newEvent = new Event(description, getTimeFromString(chat.getInput()));
                    chat.getTaskList().add(newEvent);
                    break;
                case ADD_TODO_COMMAND:
                    validateTodo(description, chat);
                    ToDo newToDo = new ToDo(description);
                    chat.getTaskList().add(newToDo);
                    break;
                default:
                    break;
            }
            printAddedDeletedTask(chat);
        } catch (InvalidTaskDescriptionException e) {
            System.out.println(INVALID_TASK_DESCRIPTION_ERROR_MSG);
        } catch (DuplicatedTaskException e) {
            System.out.println(DUPLICATED_TASK_ERROR_MSG);
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }
    }
}
