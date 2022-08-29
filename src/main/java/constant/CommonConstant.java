package constant;

public class CommonConstant {

    private CommonConstant() {}

    public static final String BLANK = "";

    public static final boolean IS_RUNNING = true;


    public static class ErrorCode {
        private ErrorCode() {}

        public static final int COMMAND_NOT_FOUND = 400;

        public static final int SERVICE_EXCEPTION = 500;
    }
}
