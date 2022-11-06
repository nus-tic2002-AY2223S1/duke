package logic.validators;

import common.enums.UpdateTypeEnum;
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
     * Return validates todo command
     *
     * @param   description
     * @param   chat
     * @throws  InvalidTaskDescriptionException
     * @throws  DuplicatedTaskException
     */
    public static void validateTodo(String description, Chat chat) throws InvalidTaskDescriptionException, DuplicatedTaskException {
        String regex = chat.getCommand() + "\\s+.+";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    /**
     * Return validates event command
     *
     * @param   description
     * @param   chat
     * @throws  InvalidTaskDescriptionException
     * @throws  DuplicatedTaskException
     */
    public static void validateEvent(String description, Chat chat) throws InvalidTaskDescriptionException, DuplicatedTaskException {
        String regex = chat.getCommand() + "\\s+(\\w+\\s+)+/" + AT + "\\s+\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    /**
     * Return validates deadline command
     *
     * @param   description
     * @param   chat
     * @throws  InvalidTaskDescriptionException
     * @throws  DuplicatedTaskException
     */
    public static void validateDeadline(String description, Chat chat) throws InvalidTaskDescriptionException, DuplicatedTaskException {
        String regex = chat.getCommand() + "\\s+(\\w+\\s+)+/" + BY + "\\s+\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    /**
     * Return validates mark command
     *
     * @param   description
     * @param   chat
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     * @throws  NotExistTaskException
     * @throws  MarkedTaskException
     */
    public static void validateMark(String description, Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException {
        String regex = chat.getCommand() + "\\s+\\d+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
        markedTaskValidation(description, chat);
    }

    /**
     * Return validates unmark command
     *
     * @param   description
     * @param   chat
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     * @throws  NotExistTaskException
     * @throws  UnmarkedTaskException
     */
    public static void validateUnmark(String description, Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, UnmarkedTaskException {
        String regex = Chat.getCommand() + "\\s+\\d+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
        unmarkedTaskValidation(description, chat);
    }

    /**
     * Return validates delete command
     *
     * @param   description
     * @param   chat
     * @throws  EmptyTaskListException
     * @throws  NotExistTaskException
     * @throws  InvalidTaskDescriptionException
     */
    public static void validateDelete(String description, Chat chat) throws EmptyTaskListException, NotExistTaskException, InvalidTaskDescriptionException {
        String regex = chat.getCommand() + "\\s+\\d+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
    }

    /**
     * Return validates list command
     *
     * @param   chat
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     */
    public static void validateList(Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException {
        String regex = chat.getCommand().toString();
        emptyTaskListValidation(chat);
        regexValidation(regex, chat);
    }

    /**
     * Return validates help command
     *
     * @param   chat
     * @throws  InvalidTaskDescriptionException
     */
    public static void validateHelp(Chat chat) throws InvalidTaskDescriptionException {
        String regex = chat.getCommand().toString();
        regexValidation(regex, chat);
    }

    /**
     * Return validates find command
     *
     * @param   chat
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     */
    public static void validateFind(Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException {
        String regex = chat.getCommand() + "\\s+.+";
        emptyTaskListValidation(chat);
        regexValidation(regex, chat);
    }

    /**
     * Return validates update command
     *
     * @param   chat
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     * @throws  NotExistTaskException
     */
    public static void validateUpdate(UpdateTypeEnum updateType, String id, Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException {
        String regex = chat.getCommand() + "\\s+" + id + "\\s+" + updateType + "\\s+.+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(id, chat);
        regexValidation(regex, chat);
    }
}
