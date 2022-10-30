package logic.validators;

import common.exceptions.EmptyTaskListException;
import common.exceptions.DuplicatedTaskException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import common.exceptions.UnmarkedTaskException;
import model.Chat;

import static common.constants.CommonConstant.AT;
import static common.constants.CommonConstant.BY;
import static common.utils.TaskValidationUtil.duplicatedTaskValidation;
import static common.utils.TaskValidationUtil.emptyTaskListValidation;
import static common.utils.TaskValidationUtil.regexValidation;
import static common.utils.TaskValidationUtil.markedTaskValidation;
import static common.utils.TaskValidationUtil.unmarkedTaskValidation;
import static common.utils.TaskValidationUtil.notExistTaskValidation;

public class Validator {
    /**
     * validateTodo validates todo command
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws InvalidTaskDescriptionException
     * @throws DuplicatedTaskException
     * @return {void}
     */
    public static void validateTodo(String description, Chat chat) throws InvalidTaskDescriptionException, DuplicatedTaskException {
        String regex = chat.getCommand() + "\\s+.+";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    /**
     * validateEvent validates event command
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws InvalidTaskDescriptionException
     * @throws DuplicatedTaskException
     * @return {void}
     */
    public static void validateEvent(String description, Chat chat) throws InvalidTaskDescriptionException, DuplicatedTaskException {
        String regex = chat.getCommand() + "\\s+\\w+\\s+/" + AT + "\\s+\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    /**
     * validateDeadline validates deadline command
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws InvalidTaskDescriptionException
     * @throws DuplicatedTaskException
     * @return {void}
     */
    public static void validateDeadline(String description, Chat chat) throws InvalidTaskDescriptionException, DuplicatedTaskException {
        String regex = chat.getCommand() + "\\s+\\w+\\s+/" + BY + "\\s+\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    /**
     * validateMark validates mark command
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @throws NotExistTaskException
     * @throws MarkedTaskException
     * @return {void}
     */
    public static void validateMark(String description, Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException {
        String regex = chat.getCommand() + "\\s+.+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
        markedTaskValidation(description, chat);
    }

    /**
     * validateUnmark validates unmark command
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @throws NotExistTaskException
     * @throws UnmarkedTaskException
     * @return {void}
     */
    public static void validateUnmark(String description, Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, UnmarkedTaskException {
        String regex = Chat.getCommand() + "\\s+.+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
        unmarkedTaskValidation(description, chat);
    }

    /**
     * validateDelete validates delete command
     *
     * @param {String} description
     * @param {Chat} chat
     * @throws EmptyTaskListException
     * @throws NotExistTaskException
     * @throws InvalidTaskDescriptionException
     * @return {void}
     */
    public static void validateDelete(String description, Chat chat) throws EmptyTaskListException, NotExistTaskException, InvalidTaskDescriptionException {
        String regex = chat.getCommand() + "\\s+.+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
    }

    /**
     * validateList validates list command
     *
     * @param {Chat} chat
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @return {void}
     */
    public static void validateList(Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException {
        String regex = chat.getCommand().toString();
        emptyTaskListValidation(chat);
        regexValidation(regex, chat);
    }

    /**
     * validateHelp validates help command
     *
     * @param {Chat} chat
     * @throws InvalidTaskDescriptionException
     * @return {void}
     */
    public static void validateHelp(Chat chat) throws InvalidTaskDescriptionException {
        String regex = chat.getCommand().toString();
        regexValidation(regex, chat);
    }
}
