package common.utils;

import common.exceptions.*;
import model.Chat;
import model.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.constants.CommonConstant.INIT_INT_VAL;

public class TaskValidationUtil {
    /**
     * regexValidation validates the string format in regular expression
     *
     * @param {String} regex
     * @param {Chat} chat
     * @throws InvalidTaskDescriptionException
     * @return {void}
     */
    public static void regexValidation(String regex, Chat chat) throws InvalidTaskDescriptionException {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(chat.getInput());

        if (!match.matches()) {
            throw new InvalidTaskDescriptionException();
        }
    }

    /**
     * duplicatedTaskValidation validates if there is duplicated task entered by user
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws DuplicatedTaskException
     * @return {void}
     */
    public static void duplicatedTaskValidation(String description, Chat chat) throws DuplicatedTaskException {
        for (Task task : chat.getTaskList()) {
            if (description.equals(task.getDescription())) {
                throw new DuplicatedTaskException();
            }
        }
    }

    /**
     * emptyTaskListValidation validates if the task list is empty
     *
     * @param {Chat} chat
     * @throws EmptyTaskListException
     * @return {void}
     */
    public static void emptyTaskListValidation(Chat chat) throws EmptyTaskListException {
        if (chat.getTaskList().isEmpty()) {
            throw new EmptyTaskListException();
        }
    }

    /**
     * notExistTaskValidation validates if task is existing in task list
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws NotExistTaskException
     * @return {void}
     */
    public static void notExistTaskValidation(String description, Chat chat) throws NotExistTaskException {
        if ((Integer.parseInt(description) - INIT_INT_VAL) >= chat.getTaskList().size()) {
            throw new NotExistTaskException();
        }
    }

    /**
     * markedTaskValidation validates if task has already been marked
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws MarkedTaskException
     * @return {void}
     */
    public static void markedTaskValidation(String description, Chat chat) throws MarkedTaskException {
        Task task = chat.getTaskList().get(Integer.parseInt(description) - INIT_INT_VAL);
        if (task.getDone()) {
            throw new MarkedTaskException();
        }
    }

    /**
     * unmarkedTaskValidation validates if task has already been unmarked
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws UnmarkedTaskException
     * @return {void}
     */
    public static void unmarkedTaskValidation(String description, Chat chat) throws UnmarkedTaskException {
        Task task = chat.getTaskList().get(Integer.parseInt(description) - INIT_INT_VAL);
        if (!task.getDone()) {
            throw new UnmarkedTaskException();
        }
    }
}
