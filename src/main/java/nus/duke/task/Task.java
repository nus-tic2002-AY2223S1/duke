package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents a task in general.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param description The description of the task.
     * @return nothing. This is a constructor.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task's description.
     *
     * @return The task's description.
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Marks a task object as done.
     *
     * @return nothing. This is a void function.
     */
    public void markAsDone() {
        if (this.isDone == false) {
            this.isDone = true;
        } else {
            System.out.println(TASK_ALREADY_MARKED_DONE_MESSAGE);
        }
    }

    /**
     * Marks a task object as NOT done.
     *
     * @return nothing. This is a void function.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task's status icon
     * "X" when the status is marked done
     * " " when the status is marked not done.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : SPACE); // mark done task with X
    }

    /**
     * Returns the task's status.
     * "true" if task was marked done.
     * "false" if task was not marked done.
     *
     * @return Status of whether the task has been completed.
     */
    public String getIsDone() {
        String isDoneStr = Boolean.toString(this.isDone);
        return isDoneStr;
    }

    /**
     * Edits the task description.
     *
     * @param description The new description of the task.
     * @return nothing. This is a void function.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Parses for the date and reformats the date into LocalDate type.
     *
     * @param userInput The task keyed in by the user.
     * @return A date in LocalDate type.
     */
    public LocalDate processDate(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = getDateInStr(userInput);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public abstract String getDateInStr(String userInput);

    public abstract LocalDate getDate();

    public abstract String getTaskType();

    public abstract String getTaskDetails();

    public abstract String getDescription(String userInput);
}