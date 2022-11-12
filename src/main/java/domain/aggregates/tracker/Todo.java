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
     * Creates a new Todo.
     *
     * @param input String.
     * @throws DukeValidationException if name is empty.
     */
    public Todo(String input) throws DukeValidationException {
        super(input);
    }

    /**
     * Creates a new Todo with explicit values for Id, Name and Is Done as it is converting values from .txt file.
     *
     * @param id Integer.
     * @param name String.
     * @param isDone boolean.
     */
    public Todo(int id, String name, boolean isDone){
        super(id, name, isDone);
    }

    /**
     * @inheritDoc
     * Prints out deadline in this format: {id}.[{shortName}] [{isDone}] {name} .
     */
    @Override
    public void printItem(){
        String displayText = String.format("\t\t%d.[%s][%s] %s", this.id, this.shortName, this.isDone ? "X":" ", this.name);
        CommonHelper.printMessage(displayText);
    }

    /**
     * @inheritDoc
     * If object of different type, it will be cast as Todo and compare .
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        Todo todo = (Todo) obj;
        return todo.shortName.equals(this.shortName) && (todo.name.toLowerCase()).equals(this.name.toLowerCase());
    }

    /**
     * @inheritDoc
     * Convert deadline to string in this format: {id} | {shortName} | {isDone} | {name} .
     */
    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s", this.id, this.shortName, CommonHelper.booleanToInteger(this.isDone), this.name);
    }

    /**
     * @inheritDoc
     * Not applicable for Todo.
     *
     * @return false.
     */
    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        return false;
    }

    /**
     * @inheritDoc
     * Not applicable for Todo.
     *
     * @throws DukeArgumentException if postpone for Todo.
     */
    @Override
    public void postpone(String remarks, boolean isSpecified) throws DukeArgumentException {
        throw new DukeArgumentException(String.format(MessageConstants.TASK_SNOOZE_NOT_APPLICABLE, this.id));
    }

    /**
     * @inheritDoc
     * Find Todos where either id or name matches.
     * If exception, returns false.
     */
    @Override
    public boolean find(String keyword) {
        boolean result = false;
        try {
            result = this.id == CommonHelper.getNumber(keyword);
        } catch (Exception ex) {
            result = this.name.toLowerCase().contains(keyword.toLowerCase());
        }
        return result;
    }

}
