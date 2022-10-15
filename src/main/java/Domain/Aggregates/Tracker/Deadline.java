package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeValidationException;

public class Deadline extends Task{
    public String dueDateTime;
    protected String shortName = "D";

    public Deadline(String n) throws DukeValidationException {
        super(n);
        String[] f = CommonHelper.formatPassedName(n, "by");
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Due Date Time"));
        this.name = f[0].trim();
        this.dueDateTime = f[1].trim();
    }

    @Override
    public void printItem(Integer n){
        String displayText = String.format("\t\t%d.[%s][%s] %s (by: %s)", n, this.shortName, this.isDone ? "X":" ", this.name, this.dueDateTime);
        CommonHelper.printMessage(displayText);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Deadline d = (Deadline) obj;
        return d.shortName.equals(this.shortName) && d.name.equals(this.name) && d.dueDateTime.equals(this.dueDateTime);
    }
    public String getDueDateTime(){
        return this.dueDateTime;
    }
    public void setDueDateTime(String t){
        this.dueDateTime = t;
    }
}
