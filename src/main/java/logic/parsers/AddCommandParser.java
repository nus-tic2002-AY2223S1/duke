package logic.parsers;

import model.Chat;
import model.Deadline;
import model.Event;
import model.ToDo;

import static common.constants.CommandConstant.ADD_DEADLINE_COMMAND;
import static common.constants.CommandConstant.ADD_EVENT_COMMAND;
import static common.constants.CommandConstant.ADD_TODO_COMMAND;
import static common.constants.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.utils.StringUtil.getFirstWord;
import static common.utils.StringUtil.getTimeFromString;
import static logic.parsers.TaskValidationParser.validateDeadline;
import static logic.parsers.TaskValidationParser.validateEvent;
import static logic.parsers.TaskValidationParser.validateTodo;

public class AddCommandParser {
    /**
     * parseAddCommand returns parse add commands such as ADD_DEADLINE_COMMAND, ADD_EVENT_COMMAND, ADD_TODO_COMMAND and default
     *
     * @param {Chat} chat
     * @return {void}
     */
    public static void parseAddCommand(String description, Chat chat) {
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
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }
    }
}
