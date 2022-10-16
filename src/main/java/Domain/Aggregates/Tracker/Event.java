package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeValidationException;

public class Event extends Task{
    protected String startDateTime;
    protected String shortName = "E";

    public Event(String n) throws DukeValidationException {
        super(n);
        String[] f = CommonHelper.formatPassedName(n, "at");
        validate(f);
        this.name = f[0].trim();
        this.startDateTime = f[1].trim();
    }

    public Event(int id, String name, String startDateTime, boolean isDone) {
        super(id, name, isDone);
        this.startDateTime = startDateTime;
    }

    @Override
    public void printItem(){
        String displayText = String.format("\t\t%d.[%s][%s] %s (at: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.startDateTime);
        CommonHelper.printMessage(displayText);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Event e = (Event)obj;
        return e.shortName.equals(this.shortName) && e.name.equals(this.name) && e.startDateTime.equals(this.startDateTime);
    }

    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name, this.startDateTime);
    }

    private void validate(String[] f) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants. TASK_VALIDATION_EMPTY_ERROR, "Start Date Time"));
    }

    public String getStartDateTime(){
        return this.startDateTime;
    }
    public void setStartDateTime(String t){
        this.startDateTime = t;
    }
}
