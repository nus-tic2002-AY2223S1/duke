package logic.parsers;

import common.exceptions.*;
import model.Chat;

import static common.constants.CommonConstant.*;
import static common.utils.TaskValidationUtil.*;

public class TaskValidationParser {
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
        String regex = chat.getCommand() + "\\s+\\w+";
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
        String regex = chat.getCommand() + "\\s+\\w+\\s+/" + AT + "\\s+.+";
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
        String regex = chat.getCommand() + "\\s+\\w+\\s+/" + BY + "\\s+.+";
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
        String regex = chat.getCommand();
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
        String regex = chat.getCommand();
        regexValidation(regex, chat);
    }
}
