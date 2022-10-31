package Duke;

import Util.DateProcessor;

public class Event extends Task {

    protected String type = "E";


    public Event(String description, long due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return (this.due == 0 ? "\t[E]" + super.toString() : "\t[E]" + super.toString() + " (at: " + DateProcessor.unixToString(this.due)  + ")");
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getIsDone() {
        return this.isDone ? "1" : "0";
    }
}