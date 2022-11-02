package duke.tasks;

import duke.utils.DateProcessor;

public class Todo extends Task {
    protected String type = "T";

    public Todo(String description, long due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return (this.due == 0 ? "\t[T]" + super.toString() : "\t[T]" + super.toString() + " (by: " + DateProcessor.unixToString(this.due) + ")");
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
