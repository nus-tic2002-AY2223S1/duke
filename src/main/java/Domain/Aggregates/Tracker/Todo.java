package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Domain.Exceptions.DukeValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Todo extends Task {
    protected String shortName = "T";

    public Todo(String n) throws DukeValidationException {
        super(n);
    }

    public Todo(int id, String name, boolean isDone){
        super(id, name, isDone);
    }

    @Override
    public void printItem(){
        String displayText = String.format("\t\t%d.[%s][%s] %s", this.id, this.shortName, this.isDone ? "X":" ", this.name);
        CommonHelper.printMessage(displayText);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Todo t = (Todo) obj;
        return t.shortName.equals(this.shortName) && t.name.equals(this.name);
    }

    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name);
    }

    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        return false;
    }
}
