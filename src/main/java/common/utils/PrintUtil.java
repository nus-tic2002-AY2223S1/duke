package common.utils;

import logic.commands.DeleteCommand;
import model.Chat;
import model.Task;

import static common.constants.CommandConstant.DELETE_COMMAND;
import static common.constants.CommonConstant.*;
import static common.constants.ErrorMessage.EMPTY_TASK_LIST_ERROR_MSG;

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
        System.out.println(LOGO);
        System.out.println(HELLO_GREETING);
        printLine();
        System.out.print(PROMPT);
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
     * printAddedDeletedTask prints when task is successfully added or deleted
     *
     * @param {String} input
     * @param {Chat} chat
     * @return {void}
     */
    public static void printAddedDeletedTask(Chat chat) {
        String commandMsg = chat.getCommand().equals(DELETE_COMMAND) ? DELETED : ADDED;
        System.out.println(String.format(commandMsg, chat.getInput()));
        chat.getTaskList().forEach(task ->
            System.out.println(TAB + task)
        );
        System.out.println(String.format(TOTAL_NO_OF_TASKS, chat.getTaskList().toArray().length));
    }

    /**
     * printMarkedTask prints when command task is successfully marked or unmarked
     *
     * @param {String} command
     * @param {String} input
     * @param {Task} task
     * @return {void}
     */
    public static void printMarkedTask(String command, String input, Task task) {
        System.out.println(String.format(command, input));
        System.out.println(TAB + task);
    }
}
