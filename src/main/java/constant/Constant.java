package constant;

public class Constant {

    private Constant() {}

    public static final String BLANK = "";

    public static final boolean IS_RUNNING = true;

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
     * @description storing error code constant
     * @author Dex
     * @date 2022/08/31
     */
    public static class ErrorCode {

        private ErrorCode() {}

        public static final int COMMAND_NOT_FOUND = 600;

        public static final int ILLEGAL_ARGUMENTS = 601;

        public static final int SERVICE_EXCEPTION = 500;
    }
}
