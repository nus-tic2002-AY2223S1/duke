package ui;

import model.Chat;
import model.Task;

import static common.constants.CommonConstant.ADDED;
import static common.constants.CommonConstant.BYE_GREETING;
import static common.constants.CommonConstant.DASHES;
import static common.constants.CommonConstant.DELETED;
import static common.constants.CommonConstant.HELLO_GREETING;
import static common.constants.CommonConstant.LOGO;
import static common.constants.CommonConstant.PROMPT;
import static common.constants.CommonConstant.TAB;
import static common.constants.CommonConstant.TOTAL_NO_OF_TASKS;
import static common.enums.CommandEnum.delete;

public class ConsoleUi implements Ui {
    /**
     * Return prints line
     */
    public void lineSeparator() {
        System.out.println(DASHES);
    }

    /**
     * Return prints the prefix bot for prompting user
     */
    public void prompt() {
        System.out.print(PROMPT);
    }

    /**
     * Return prints bye statement
     */
    public void printBye() {
        System.out.println(BYE_GREETING);
    }

    /**
     * Return prints greeting text
     */
    public void printGreet() {
        System.out.println(LOGO);
        System.out.println(HELLO_GREETING);
        lineSeparator();
        prompt();
    }

    /**
     * Return prints when task is successfully added or deleted
     *
     * @param   chat
     */
    public void printAddedDeletedTask(Chat chat) {
        String commandMsg = chat.getCommand().equals(delete) ? DELETED : ADDED;
        System.out.println(String.format(commandMsg, chat.getInput()));
        chat.getTaskList().forEach(task ->
                System.out.println(TAB + task)
        );
        System.out.println(String.format(TOTAL_NO_OF_TASKS, chat.getTaskList().toArray().length));
    }

    /**
     * Return prints when command task is successfully marked or unmarked
     *
     * @param   command
     * @param   input input from user
     * @param   task
     */
    public void printMarkedTask(String command, String input, Task task) {
        System.out.println(String.format(command, input));
        System.out.println(TAB + task);
    }
}
