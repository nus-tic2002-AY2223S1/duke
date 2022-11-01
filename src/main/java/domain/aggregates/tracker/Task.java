package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;

public abstract class Task {
    /**
     * Properties
     */
    protected int id;
    protected String name;
    protected boolean isDone;

    /**
     * Task default constructor.
     * Name is mandatory
     * Is Done is set to false by default
     */
    public Task(String n) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(n))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        this.name = n.trim();
        this.isDone = false;
    }

    /**
     * Task constructor when converting values from .txt file to a Task object
     */
    public Task(int id, String name, boolean isDone){
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Abstract method to be overwritten for printing Task
     */
    public abstract void printItem();

    /**
     * Abstract method to be overwritten for checking if 2 objects are equal
     */
    public abstract boolean equals(Object t);

    /**
     * Abstract method to be overwritten for converting object to string
     */
    public abstract String toString();

    /**
     * Abstract method to be overwritten for getting Task within the date range
     */
    public abstract boolean compare(LocalDate start, LocalDate end);

    public abstract void update(String remarks) throws DukeValidationException;

    /**
     * Getters & Setters for properties
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
