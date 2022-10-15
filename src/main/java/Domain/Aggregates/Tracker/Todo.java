package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Domain.Exceptions.DukeValidationException;

public class Todo extends Task {
    protected String shortName = "T";
    public Todo(String n) throws DukeValidationException {
        super(n);
    }

    @Override
    public void printItem(Integer n){
        String displayText = String.format("\t\t%d.[%s][%s] %s", n, this.shortName, this.isDone ? "X":" ", this.name);
        CommonHelper.printMessage(displayText);
    }
}
