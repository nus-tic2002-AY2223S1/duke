package common.utils;

import model.Chat;
import model.Task;

import static common.constants.CommonConstant.ADDED;
import static common.constants.CommonConstant.BYE_GREETING;
import static common.constants.CommonConstant.DASHES;
import static common.constants.CommonConstant.HELLO_GREETING;
import static common.constants.CommonConstant.INPUT_OPTIONS;
import static common.constants.CommonConstant.TAB;
import static common.constants.CommonConstant.TOTAL_NO_OF_TASKS;
import static common.constants.ErrorMessage.TASK_LIST_EMPTY_ERROR_MSG;

public class PrintUtil {
    /**
     * printBye prints bye statement
     *
     * @return {void}
     */
    public static void printBye() {
        System.out.println(BYE_GREETING);
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
        System.out.println(TASK_LIST_EMPTY_ERROR_MSG);
    }

    /**
     * printAddedTask prints when task is successfully added
     *
     * @param {String} input
     * @param {Chat} chat
     * @return {void}
     */
    public static void printAddedTask(Chat chat) {
        System.out.println(String.format(ADDED, chat.getInput()));
        chat.getTaskList().forEach(task ->
            System.out.println(TAB + task)
        );
        System.out.println(String.format(TOTAL_NO_OF_TASKS, chat.getTaskList().toArray().length));
    }

    /**
     * printCompletedTask prints when command task is successfully performed
     *
     * @param {String} command
     * @param {String} input
     * @param {Task} task
     * @return {void}
     */
    public static void printCompletedTask(String command, String input, Task task) {
        System.out.println(String.format(command, input));
        System.out.println(TAB + task);
    }
}
