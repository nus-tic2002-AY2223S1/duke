package common.util;

import model.Chat;
import model.Task;

import static common.constant.CommonConstant.ADDED;
import static common.constant.CommonConstant.BYE_GREETING;
import static common.constant.CommonConstant.DASHES;
import static common.constant.CommonConstant.HELLO_GREETING;
import static common.constant.CommonConstant.INPUT_OPTIONS;
import static common.constant.CommonConstant.TAB;
import static common.constant.CommonConstant.TOTAL_NO_OF_TASKS;
import static common.constant.ErrorMessage.TASK_LIST_EMPTY_ERROR_MSG;

public class PrintUtil {
    /**
     * printBye prints bye statement
     *
     * @return {void}
     */
    public static void printBye() {
        printLine();
        System.out.println(BYE_GREETING);
        printLine();
    }

    /**
     * printGreet prints greeting text
     *
     * @return {void}
     */
    public static void printGreet() {
        System.out.println(HELLO_GREETING);
        System.out.println(INPUT_OPTIONS);
        printLine();
    }

    /**
     * printLine prints line
     *
     * @return {void}
     */
    public static void printLine() {
        System.out.println(DASHES);
    }

    /**
     * printEmptyTaskList prints error message of empty task list
     *
     * @return {void}
     */
    public static void printEmptyTaskList() {
        printLine();
        System.out.println(TASK_LIST_EMPTY_ERROR_MSG);
        printLine();
    }

    public static void printAddedTask(String input, Chat chat) {
        System.out.println(String.format(ADDED, chat.getInput()));
        chat.getTaskList().forEach(task -> {
            if (!task.getDescription().equals(input)) {
                return;
            }
            System.out.println(TAB + task);
        });
        System.out.println(String.format(TOTAL_NO_OF_TASKS, chat.getTaskList().toArray().length));
    }

    public static void printCompletedTask(String command, String input, Task task) {
        System.out.println(String.format(command, input));
        System.out.println(TAB + task);
    }
}
