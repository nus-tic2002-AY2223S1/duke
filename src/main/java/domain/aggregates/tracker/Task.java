package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.*;

import java.time.LocalDate;

public abstract class Task {
    /**
     * Properties
     */
    protected int id;
    protected String name;
    protected boolean isDone;

    /**
     * Creates a new Task with Name and Is Done flag to false.
     *
     * @param input String.
     * @throws DukeValidationException if name or start date time properties are empty.
     */
    public Task(String input) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(input)) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        }
        this.name = input.trim();
        this.isDone = false;
    }

    /**
     * Creates a new Task with explicit values for Id, Name and Is Done as it is converting values from .txt file.
     *
     * @param id Integer.
     * @param name String.
     * @param isDone boolean.
     */
    public Task(int id, String name, boolean isDone){
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Prints task in a friendly format.
     */
    public abstract void printItem();

    /**
     * Checks if 2 tasks are equal.
     *
     * @param task Object.
     * @return boolean.
     */
    public abstract boolean equals(Object task);

    /**
     * Converts task object to string.
     *
     * @return String.
     */
    public abstract String toString();

    /**
     * Checks if task is within the date range.
     *
     * @param start LocalDate.
     * @param end LocalDate.
     * @return boolean.
     */
    public abstract boolean compare(LocalDate start, LocalDate end);

    /**
     * Postpones task based on passed date or by default by plus 1 day.
     *
     * @param date String.
     * @param isDateSpecified boolean.
     * @throws DukeValidationException if remarks is empty.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public abstract void postpone(String date, boolean isDateSpecified) throws DukeValidationException, DukeArgumentException;

    /**
     * Finds Task that contains keyword.
     *
     * @param keyword String.
     * @return boolean.
     */
    public abstract boolean find(String keyword);

    /**
     * Getters & Setters for properties.
     */
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
