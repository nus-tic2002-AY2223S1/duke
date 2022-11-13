package duke.constant;

/**
 * Class which used to store all the constants variable in the program.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class Constant {

    private Constant() {}

    /**
     * Empty string.
     */
    public static final String BLANK = "";

    /**
     * Flag to label if the program is running.
     */
    public static final boolean IS_RUNNING = true;

    /**
     * Store the maximum threads in the async pool.
     */
    public static final int MAX_THREAD = 2;

    /**
     * Common message template to display when task is added to program successfully.
     */
    public static final String TASK_ADD_SUCCESS_MSG_TEMPLATE = "Noted. I've add this task:%n" +
            "%s%n" +
            "Now you have %d tasks in the list";

    /**
     * Logo when program starts.
     */
    public static final String STARTING_LOGO =
    " .----------------.  .----------------.  .----------------.  .----------------. \n" +
    "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
    "| |  ________    | || | _____  _____ | || |  ___  ____   | || |  _________   | |\n" +
    "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
    "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
    "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
    "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
    "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
    "| |              | || |              | || |              | || |              | |\n" +
    "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
    " '----------------'  '----------------'  '----------------'  '----------------' ";

    /**
     * Logo when program terminates.
     */
    public static final String ENDING_LOGO =
    ".______   ____    ____  _______ \n" +
    "|   _  \\  \\   \\  /   / |   ____|\n" +
    "|  |_)  |  \\   \\/   /  |  |__   \n" +
    "|   _  <    \\_    _/   |   __|  \n" +
    "|  |_)  |     |  |     |  |____ \n" +
    "|______/      |__|     |_______|";

    /**
     * Class which stores task related constant.
     *
     * @author Dex
     * @date 2022/09/02
     */
    public static class Task {

        private Task() {}

        /**
         * Type label for todo task.
         */
        public static final String TYPE_TODO = "T";

        /**
         * Type label for deadline task.
         */
        public static final String TYPE_DEADLINE = "D";

        /**
         * Type label for event task.
         */
        public static final String TYPE_EVENT = "E";

        /**
         * Constant value for string value of description.
         */
        public static final String DESCRIPTION_FIELD = "description";

        /**
         * Constant value for string value of done.
         */
        public static final String DONE_FIELD = "done";
    }

    /**
     * Class which stores time related constant.
     *
     * @author Dex
     * @date 2022/09/02
     */
    public static class Time {

        private Time() {}

        /**
         * Time pattern expected to get from user.
         */
        public static final String INPUT_FORMAT = "yyyy-MM-dd HH:mm";

        /**
         * Time pattern to render in user interface.
         */
        public static final String DISPLAY_FORMAT = "dd MMM yyyy, EEE HH:mm";
    }

    /**
     * Class which stores time response code constant.
     *
     * @author Dex
     * @date 2022/09/02
     */
    public static class ResponseCode {

        private ResponseCode() {}

        /**
         * Code for success, no error occurs.
         */
        public static final int SUCCESS = 200;

        /**
         * Code for invalid command name entered.
         */
        public static final int ERROR_COMMAND_NOT_FOUND = 600;

        /**
         * Code for invalid command name entered.
         */
        public static final int ERROR_ILLEGAL_ARGUMENTS = 601;

        /**
         * Code for modifying empty task list.
         */
        public static final int ERROR_EMPTY_TASK_LIST = 602;

        /**
         * Code for rescheduling task with invalid type.
         */
        public static final int ERROR_INVALID_RESCHEDULE_TASK = 603;

        /**
         * Code for invalid index entered.
         */
        public static final int ERROR_INDEX_EXCEED_MAX_TASK_SIZE = 605;

        /**
         * Code for general service exception.
         */
        public static final int ERROR_SERVICE_EXCEPTION = 500;
    }

    public static final class ErrorMsg {

        private ErrorMsg() {}

        public static final String INDEX_OUT_BOUND = "given index is invalid, it should be more than 0";
        public static final String INDEX_NOT_A_NUMBER = "given index is invalid, index should be a positive number";
    }
}
