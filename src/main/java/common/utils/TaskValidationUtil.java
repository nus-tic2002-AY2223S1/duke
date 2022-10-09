package common.utils;

import model.Chat;
import model.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.constants.CommonConstant.PROMPT;
import static common.constants.ErrorMessage.DUPLICATED_TASK_ERROR_MSG;
import static common.constants.ErrorMessage.EMPTY_TASK_DESCRIPTION_ERROR_MSG;
import static common.constants.ErrorMessage.INVALID_TASK_COMMAND_MSG;
import static common.constants.ErrorMessage.NOT_EXIST_TASK_ERROR_MSG;
import static common.utils.PrintUtil.printEmptyTaskList;
import static common.utils.PrintUtil.printLine;
import static logic.parsers.Parser.parseChat;

public class TaskValidationUtil {
    public static void invalidTaskCommandValidation() {
        System.out.println(INVALID_TASK_COMMAND_MSG);
    }

    public static void regexValidation(String regex, Chat chat) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(chat.getInput());

        if (!match.matches()) {
            System.out.println(EMPTY_TASK_DESCRIPTION_ERROR_MSG);
            printLine();
            System.out.print(PROMPT);
            parseChat(chat.getUserInput(), chat.getTaskList());
        }
    }

    public static void duplicatedTaskValidation(String description, Chat chat) {
        for (Task task : chat.getTaskList()) {
            if (description.equals(task.getDescription())) {
                System.out.println(String.format(DUPLICATED_TASK_ERROR_MSG, chat.getInput()));
                printLine();
                System.out.print(PROMPT);
                parseChat(chat.getUserInput(), chat.getTaskList());
            }
        }
    }

    public static void emptyTaskListValidation(Chat chat) {
        if (chat.getTaskList().isEmpty()) {
            printEmptyTaskList();
            printLine();
            System.out.print(PROMPT);
            parseChat(chat.getUserInput(), chat.getTaskList());
        }
    }

    public static void notExistTaskValidation(String description, Chat chat) {
        if (chat.getTaskList().contains(description)) {
            System.out.println(NOT_EXIST_TASK_ERROR_MSG);
            printLine();
            System.out.print(PROMPT);
            parseChat(chat.getUserInput(), chat.getTaskList());
        }
    }
}
