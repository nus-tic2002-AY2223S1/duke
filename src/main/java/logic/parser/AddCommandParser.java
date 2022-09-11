package logic.parser;

import model.Chat;
import model.Deadline;
import model.Event;
import model.Task;
import model.ToDo;

import static common.constant.CommandConstant.ADD_DEADLINE_COMMAND;
import static common.constant.CommandConstant.ADD_EVENT_COMMAND;
import static common.constant.CommandConstant.ADD_TODO_COMMAND;
import static common.constant.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.util.StringUtil.getDescriptionFromString;
import static common.util.StringUtil.getFirstWord;
import static common.util.StringUtil.getTimeFromString;

public class AddCommandParser {
    public static void parseAddCommand(Chat chat) {
        try {
            switch (getFirstWord(chat.getInput())) {
                case ADD_DEADLINE_COMMAND:
                    Deadline newDeadline = new Deadline(getDescriptionFromString(ADD_DEADLINE_COMMAND, chat.getInput()),
                            getTimeFromString(chat.getInput()));
                    chat.getTaskList().add(newDeadline);
                    break;
                case ADD_EVENT_COMMAND:
                    Event newEvent = new Event(getDescriptionFromString(ADD_EVENT_COMMAND, chat.getInput()),
                            getTimeFromString(chat.getInput()));
                    chat.getTaskList().add(newEvent);
                    break;
                case ADD_TODO_COMMAND:
                    ToDo newToDo = new ToDo(getDescriptionFromString(ADD_TODO_COMMAND, chat.getInput()));
                    chat.getTaskList().add(newToDo);
                    break;
                default:
                    Task newTask = new Task(getDescriptionFromString("", chat.getInput()));
                    chat.getTaskList().add(newTask);
                    break;
            }
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }
    }
}
