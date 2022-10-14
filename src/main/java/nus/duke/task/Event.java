package nus.duke;

public class Event extends Task {
    protected String at; // i.e. at a specific date/time

    public Event(String wish) {
        super(wish);
        this.at = wish;
    }

    @Override
    public String getTaskType(){
        return "E";
    }
}
