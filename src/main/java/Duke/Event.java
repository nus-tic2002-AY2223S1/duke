package Duke;

import Util.DateProcessor;

public class Event extends Task {
    protected String type = "E";

    public Event(String description, long[] due) {
        super(description);

        if(due.length == 0){
            this.due = 0;
            return;
        }

        this.due = due[0];
        if (due.length != 1){
            this.to = due[1];
        }
    }

    @Override
    public String toString() {
        if(this.due == 0){
            return "\t[E]" + super.toString();
        }

        if (this.to == 0){
            return "\t[E]" + super.toString() + " (at: " + DateProcessor.unixToString(this.due)  + ")";
        }
        return "\t[E]" + super.toString() + " (" + DateProcessor.unixToString(this.due)  +" ~ "+ DateProcessor.unixToString(this.to) + ")";
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