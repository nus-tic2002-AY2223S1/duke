package duke.dto;

/**
 * Data transfer object used to store the response data after executing the command.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class ResponseDto<T> {

    /**
     * Executed command name.
     */
    private String commandName;

    /**
     * Message to display.
     */
    private String message;

    /**
     * Response data rendered in the user interface.
     */
    private T data;

    /**
     * Default constructor.
     */
    public ResponseDto() {}

    /**
     * Multi args constructor.
     *
     * @param message: Message to display in user interface.
     */
    public ResponseDto(String message) {
        this.message = message;
    }

    /**
     * Multi args constructor.
     *
     * @param commandName: Command name.
     * @param message: Message to display in user interface.
     */
    public ResponseDto(String commandName, String message) {
        this.commandName = commandName;
        this.message = message;
    }

    /**
     * Multi args constructor.
     *
     * @param commandName: Command name.
     * @param data: Response data to render.
     */
    public ResponseDto(String commandName, T data) {
        this.commandName = commandName;
        this.data = data;
    }

    /**
     * Multi args constructor.
     *
     * @param commandName: Command name.
     * @param message: Message to display in user interface.
     * @param data: Response data to render.
     */
    public ResponseDto(String commandName, String message, T data) {
        this.commandName = commandName;
        this.message = message;
        this.data = data;
    }

    /**
     * Get command name.
     *
     * @return Name of command.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Set command name.
     *
     * @param commandName: Target command name.
     */
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Get message.
     *
     * @return Message to render.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set message.
     *
     * @param message: Message to render.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get render data.
     *
     * @return Data to render.
     */
    public T getData() {
        return data;
    }

    /**
     * Set render data.
     *
     * @param data: Target render data.
     */
    public void setData(T data) {
        this.data = data;
    }
}
