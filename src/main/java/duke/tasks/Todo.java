package duke.tasks;

import duke.exceptions.DukeException;

public class Todo extends Task {
    
    protected boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    public Todo(String d) throws DukeException {
        super(d);
        isDone = false;
    }
};
