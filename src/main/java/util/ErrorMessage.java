package util;

public enum ErrorMessage {
    
    ERROR_MESSAGE_EMPTY_CONTENT("☹ OOPS!!! The content cannot be empty."),
    ERROR_MESSAGE_INVALID_ACTION("☹ OOPS!!! I'm sorry, but I don't know what that means :-("),
    ERROR_MESSAGE_ALREADY_MARKED("☹ OOPS!!! This task already been marked as completed."),
    ERROR_MESSAGE_ALREADY_UNMARKED("☹ OOPS!!! This task already been marked as incomplete."),
    ERROR_MESSAGE_DELETE_NOT_NUMBER("☹ OOPS!!! The description of the delete action must be a number."),
    ERROR_MESSAGE_DELETE_INDEX_OUT_OF_RANGE("☹ OOPS!!! The description of a delete action must be a number that is not larger than the list size."),
    ERROR_MESSAGE_DESCRIPTION_EMPTY("☹ OOPS!!! The description of the task cannot be empty."),
    ERROR_MESSAGE_DATETIME_EMPTY("☹ OOPS!!! The due date/time of the task cannot be empty."),
    ERROR_MESSAGE_INVALID_DATETIME_FORMAT("☹ OOPS!!! The input date time format is incorrect.");
    
    private String msg;
    
    ErrorMessage(String msg) {
        this.msg = msg;
    }
    
    @Override
    public String toString() {
        return this.msg;
    }
}
