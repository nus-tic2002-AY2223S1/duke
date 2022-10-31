package Duke;

import Util.DateProcessor;

public class Deadline extends Task {
    protected String type = "D";

    public Deadline(String description, long due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return (this.due == 0 ? "\t[D]" + super.toString() : "\t[D]" + super.toString() + " (by: " + DateProcessor.unixToString(this.due)  + ")");
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