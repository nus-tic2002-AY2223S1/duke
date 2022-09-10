package common.util;

import static common.constant.CommonConstant.BYE_GREETING;
import static common.constant.CommonConstant.DASHES;
import static common.constant.CommonConstant.HELLO_GREETING;
import static common.constant.CommonConstant.INPUT_OPTIONS;
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
     * printFormatString prints string with format
     *
     * @param {String} printStatement
     * @param {String} input
     * @return {void}
     */
    public static void printFormatString(String printStatement, String input) {
        System.out.println(String.format(printStatement, input));
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
}
