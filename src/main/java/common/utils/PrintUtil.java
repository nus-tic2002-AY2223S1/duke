package common.utils;

import logic.commands.DeleteCommand;
import model.Chat;
import model.Task;

import static common.constants.CommandConstant.DELETE_COMMAND;
import static common.constants.CommonConstant.*;
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
        String commandMsg = chat.getCommand().equals(DELETE_COMMAND) ? DELETED : ADDED;
        System.out.println(String.format(commandMsg, chat.getInput()));
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
