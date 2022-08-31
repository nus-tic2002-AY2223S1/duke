package constant;

public class CommonConstant {

    private CommonConstant() {}

    public static final String BLANK = "";

    public static final boolean IS_RUNNING = true;

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
