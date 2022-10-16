package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeValidationException;

public abstract class Task {
    protected int id;
    protected String name;
    protected boolean isDone;

    public Task(int i, String n) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(n))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        this.id = i;
        this.name = n.trim();
        this.isDone = false;
    }

    public Task(int id, String name, boolean isDone){
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    public abstract void printItem();
    public abstract boolean equals(Object t);
    public abstract String toString();

    public int getId() { return this.id; }
    public void setId(int i) { this.id = i; }
    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name = n;
    }
    public boolean getIsDone(){
        return this.isDone;
    }
    public void setIsDone(boolean d){
        this.isDone = d;
    }


}
