package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeArgumentException;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;

public class Todo extends Task {
    /**
     * Properties
     */
    protected String shortName = "T";

    /**
     * Todo is a Task
     * Todo default constructor
     */
    public Todo(String n) throws DukeValidationException {
        super(n);
    }
    /**
     * Default constructor when converting values from .txt file to a Todo object
     */
    public Todo(int id, String name, boolean isDone){
        super(id, name, isDone);
    }

    /**
     * Abstract method that is overwritten
     * To print out task in this format: {id}.[{shortName}] [{isDone}] {name}
     */
    @Override
    public void printItem(){
        String displayText = String.format("\t\t%d.[%s][%s] %s", this.id, this.shortName, this.isDone ? "X":" ", this.name);
        CommonHelper.printMessage(displayText);
    }

    /**
     * Abstract method that is overwritten
     * Compares 2 objects to see if they are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Todo t = (Todo) obj;
        return t.shortName.equals(this.shortName) && t.name.equals(this.name);
    }

    /**
     * Abstract method that is overwritten
     * To convert object to string in this format: {id} | {shortName} | {isDone} | {name}
     */
    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name);
    }

    /**
     * Abstract method that is overwritten
     * Not applicable for Todo
     * Hence, return false by default
     */
    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        return false;
    }

    @Override
    public boolean find(String keyword) {
        boolean result = false;
        try {
            result = this.id == CommonHelper.getNumber(keyword);
        } catch (Exception ex)
        {
            result = this.name.toLowerCase().contains(keyword.toLowerCase());
        }
        return result;
    }

    @Override
    public void update(String remarks, boolean isSpecified) throws DukeArgumentException {
        throw new DukeArgumentException(String.format(MessageConstants.TASK_SNOOZE_NOT_APPLICABLE, this.id));
    }
}
