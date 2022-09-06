package ui;

public interface UI {

    /**
     * @description display greeting message when program starts
     * @author Dex
     * @date 2022/08/31
     */
    void displayGreeting();

    /**
     * @description display line break, implement it for console UI
     * @author Dex
     * @date 2022/08/31
     */
    void displayLineBreak();

    /**
     * @description format duke exception message to prevent actual cause expose to user
     * @author Dex
     * @date 2022/08/31
     * @param errorMsg: program error message
     */
    void displayDukeErrorMsg(String errorMsg);

    /**
     * @description display message for unknown exception, hide the actual root cause to user
     * @author Dex
     * @date 2022/09/06
     * @param throwable: exception instance
     */
    void displayUnknownErrorMsg(Throwable throwable);

    /**
     * @description get user input in raw string
     * @author Dex
     * @date 2022/08/31
     */
    String getInput();
}
