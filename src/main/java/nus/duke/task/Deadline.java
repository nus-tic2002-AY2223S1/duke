package nus.duke.task;

public class Deadline extends Task {
    protected String by; // i.e. by a certain date or before a certain date

    public Deadline(String userInput) {
        super(userInput);
        this.by = formatDateTime(userInput);
    }
    @Override
    public String getTaskType(){
        return "D";
    }
}
