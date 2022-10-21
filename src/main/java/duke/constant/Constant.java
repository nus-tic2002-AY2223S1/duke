package duke.constant;

public class Constant {

    private Constant() {}

    public static final String BLANK = "";

    public static final boolean IS_RUNNING = true;

    public static final int MAX_THREAD = 2;

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

    public static final String ENDING_LOGO =
    ".______   ____    ____  _______ \n" +
    "|   _  \\  \\   \\  /   / |   ____|\n" +
    "|  |_)  |  \\   \\/   /  |  |__   \n" +
    "|   _  <    \\_    _/   |   __|  \n" +
    "|  |_)  |     |  |     |  |____ \n" +
    "|______/      |__|     |_______|";

    /**
     * @description storing task related constant
     * @author Dex
     * @date 2022/09/02
     */
    public static class Task {

        private Task() {}

        public static final String TYPE_TODO = "T";

        public static final String TYPE_DEADLINE = "D";

        public static final String TYPE_EVENT = "E";


        public static final String DESCRIPTION_FIELD = "description";

        public static final String DONE_FIELD = "done";
    }

    /**
     * @description storing time related constant
     * @author Dex
     * @date 2022/09/02
     */
    public static class Time {

        private Time() {}

        public static final String INPUT_FORMAT = "yyyy-MM-dd HH:mm";

        public static final String DISPLAY_FORMAT = "dd MMM yyyy, EEE HH:mm";
    }

    /**
     * @description storing code constant
     * @author Dex
     * @date 2022/08/31
     */
    public static class ResponseCode {

        private ResponseCode() {}

        public static final int SUCCESS = 200;
        public static final int ERROR_COMMAND_NOT_FOUND = 600;
        public static final int ERROR_ILLEGAL_ARGUMENTS = 601;
        public static final int ERROR_EMPTY_TASK_LIST = 602;
        public static final int ERROR_INVALID_RESCHEDULE_TASK = 603;
        public static final int ERROR_INDEX_EXCEED_MAX_TASK_SIZE = 605;
        public static final int ERROR_SERVICE_EXCEPTION = 500;
    }
}
