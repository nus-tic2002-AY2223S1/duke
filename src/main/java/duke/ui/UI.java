package duke.ui;

import duke.dto.ResponseDto;

/**
 * Interface which used to be implemented by different user interface application.
 *
 * @author Dex
 * @date 2022/10/26
 */
public interface UI {

    /**
     * Display greeting message when program starts.
     */
    void renderGreetingMessage();

    /**
     * Format duke exception message to prevent actual cause expose to user.
     *
     * @param errorMsg: Program error message.
     */
    void renderDukeErrorMsg(String errorMsg);

    /**
     * Display message for unknown exception, hide the actual root cause to user.
     *
     * @param throwable: Exception instance.
     */
    void renderUnknownErrorMsg(Throwable throwable);

    /**
     * Display the message & data in response entity.
     *
     * @param responseDto: Response entity.
     */
    void renderResponse(ResponseDto<?> responseDto);

    /**
     * Get user input in raw string.
     *
     * @return Raw input from user .
     */
    String getDukeCommandInput();
}
