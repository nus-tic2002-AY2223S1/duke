package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeValidationException;

public class Event extends Task{
    protected String startDateTime;
    protected String shortName = "E";

    public Event(String n) throws DukeValidationException {
        super(n);
        String[] f = CommonHelper.formatPassedName(n);
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants. TASK_VALIDATION_EMPTY_ERROR, "Start Date Time"));
        this.name = f[0].trim();
        this.startDateTime = f[1].trim();
    }

    @Override
    public void printItem(Integer n){
        String displayText = String.format("\t\t%d.[%s][%s] %s (at: %s)", n, this.shortName, this.isDone ? "X":" ", this.name, this.startDateTime);
        CommonHelper.printMessage(displayText);
    }

    public String getStartDateTime(){
        return this.startDateTime;
    }
    public void setStartDateTime(String t){
        this.startDateTime = t;
    }
}
