package nus.duke;

public class Deadline extends Task {
    protected String by; // i.e. by a certain date or before a certain date

    public Deadline(String wish) {
        super(wish);
        this.by = wish;
    }
    @Override
    public String getTaskType(){
        return "D";
    }
}
