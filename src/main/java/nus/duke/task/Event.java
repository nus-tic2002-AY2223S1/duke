package nus.duke.task;

public class Event extends Task {
    protected String at; // i.e. at a specific date/time

    public Event(String userInput) {
        super(userInput);
        this.at = formatDateTime(userInput);
    }

    @Override
    public String getTaskType(){
        return "E";
    }
}
