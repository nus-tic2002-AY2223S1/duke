package duke.dto;

public class ResponseDto<T> {

    private String commandName;
    private String message;
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(String commandName, String message) {
        this.commandName = commandName;
        this.message = message;
    }

    public ResponseDto(String commandName, T data) {
        this.commandName = commandName;
        this.data = data;
    }

    public ResponseDto(String commandName, String message, T data) {
        this.commandName = commandName;
        this.message = message;
        this.data = data;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
