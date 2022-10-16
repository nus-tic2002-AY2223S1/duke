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
        validate(f);
        this.name = f[0].trim();
        this.dueDateTime = f[1].trim();
    }

    public Deadline(int id, String name, String dueDateTime, boolean isDone) {
        super(id, name, isDone);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public void printItem(){
        String displayText = String.format("\t\t%d.[%s][%s] %s (by: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.dueDateTime);
        CommonHelper.printMessage(displayText);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Deadline d = (Deadline) obj;
        return d.shortName.equals(this.shortName) && d.name.equals(this.name) && d.dueDateTime.equals(this.dueDateTime);
    }

    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name, this.dueDateTime);
    }

    private void validate(String[] f) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Due Date Time"));
    }

    public String getDueDateTime(){
        return this.dueDateTime;
    }
    public void setDueDateTime(String t){
        this.dueDateTime = t;
    }
}
