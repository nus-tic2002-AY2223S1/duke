package common.utils;

import common.exceptions.DuplicatedTaskException;
import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import common.exceptions.UnmarkedTaskException;
import model.Chat;
import model.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.constants.CommonConstant.INIT_INT_VAL;

public class TaskValidationUtil {
    /**
     * Return validates the string format in regular expression
     *
     * @param   regex   regular expression of the respective validation
     * @param   chat
     * @throws  InvalidTaskDescriptionException
     */
    public static void regexValidation(String regex, Chat chat) throws InvalidTaskDescriptionException {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(chat.getInput());

        if (!match.matches()) {
            throw new InvalidTaskDescriptionException();
        }
    }

    /**
     * Return validates if there is duplicated task entered by user
     *
     * @param   description description from user input
     * @param   chat
     * @throws  DuplicatedTaskException
     */
    public static void duplicatedTaskValidation(String description, Chat chat) throws DuplicatedTaskException {
        for (Task task : chat.getTaskList()) {
            if (description.equals(task.getDescription())) {
                throw new DuplicatedTaskException();
            }
        }
    }

    /**
     * Return validates if the task list is empty
     *
     * @param   chat
     * @throws  EmptyTaskListException
     */
    public static void emptyTaskListValidation(Chat chat) throws EmptyTaskListException {
        if (chat.getTaskList().isEmpty()) {
            throw new EmptyTaskListException();
        }
    }

    /**
     * Return validates if task is existing in task list
     *
     * @param   description description from user input
     * @param   chat
     * @throws  NotExistTaskException
     */
    public static void notExistTaskValidation(String description, Chat chat) throws NotExistTaskException {
        if ((Integer.parseInt(description) - INIT_INT_VAL) >= chat.getTaskList().size()) {
            throw new NotExistTaskException();
        }
    }

    /**
     * Return validates if task has already been marked
     *
     * @param   description description from user input
     * @param   chat
     * @throws  MarkedTaskException
     */
    public static void markedTaskValidation(String description, Chat chat) throws MarkedTaskException {
        Task task = chat.getTaskList().get(Integer.parseInt(description) - INIT_INT_VAL);
        if (task.getDone()) {
            throw new MarkedTaskException();
        }
    }

    /**
     * Return validates if task has already been unmarked
     *
     * @param   description description from user input
     * @param   chat
     * @throws  UnmarkedTaskException
     */
    public static void unmarkedTaskValidation(String description, Chat chat) throws UnmarkedTaskException {
        Task task = chat.getTaskList().get(Integer.parseInt(description) - INIT_INT_VAL);
        if (!task.getDone()) {
            throw new UnmarkedTaskException();
        }
    }
}
