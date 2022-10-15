package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeValidationException;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String n) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(n))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        this.name = n.trim();
        this.isDone = false;
    }

    public abstract void printItem(Integer n);

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
