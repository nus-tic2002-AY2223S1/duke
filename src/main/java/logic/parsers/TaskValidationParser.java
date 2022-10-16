package logic.parsers;

import model.Chat;

import static common.constants.CommonConstant.AT;
import static common.constants.CommonConstant.BY;
import static common.utils.TaskValidationUtil.duplicatedTaskValidation;
import static common.utils.TaskValidationUtil.emptyTaskListValidation;
import static common.utils.TaskValidationUtil.notExistTaskValidation;
import static common.utils.TaskValidationUtil.regexValidation;

public class TaskValidationParser {
    // todo
    public static void validateTodo(String description, Chat chat) {
        String regex = chat.getCommand() + "\\s+\\w+";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    // event
    public static void validateEvent(String description, Chat chat) {
        String regex = chat.getCommand() + "\\s+\\w+\\s+/" + AT + "\\s+.+";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    // deadline
    public static void validateDeadline(String description, Chat chat) {
        String regex = chat.getCommand() + "\\s+\\w+\\s+/" + BY + "\\s+.+";
        regexValidation(regex, chat);
        duplicatedTaskValidation(description, chat);
    }

    // mark
    public static void validateMark(String description, Chat chat) {
        String regex = chat.getCommand() + "\\s+.+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
    }

    // unmark
    public static void validateUnmark(String description, Chat chat) {
        String regex = chat.getCommand() + "\\s+.+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
    }

    // delete
    public static void validateDelete(String description, Chat chat) {
        String regex = chat.getCommand() + "\\s+.+";
        emptyTaskListValidation(chat);
        notExistTaskValidation(description, chat);
        regexValidation(regex, chat);
    }

    // list
    public static void validateList(Chat chat) {
        String regex = chat.getCommand();
        emptyTaskListValidation(chat);
        regexValidation(regex, chat);
    }
}
