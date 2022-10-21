package duke.ui;

import duke.dto.ResponseDto;

public interface UI {

    /**
     * @description display greeting message when program starts
     * @author Dex
     * @date 2022/08/31
     */
    void renderGreetingMessage();

    /**
     * @description format duke exception message to prevent actual cause expose to user
     * @author Dex
     * @date 2022/08/31
     * @param errorMsg: program error message
     */
    void renderDukeErrorMsg(String errorMsg);

    /**
     * @description display message for unknown exception, hide the actual root cause to user
     * @author Dex
     * @date 2022/09/06
     * @param throwable: exception instance
     */
    void renderUnknownErrorMsg(Throwable throwable);

    /**
     * @description display the message & data in response entity
     * @author Dex
     * @date 2022/10/21
     * @param responseDto: response entity
     */
    void renderResponse(ResponseDto<?> responseDto);

    /**
     * @description get user input in raw string
     * @author Dex
     * @date 2022/08/31
     */
    String getDukeCommandInput();
}
