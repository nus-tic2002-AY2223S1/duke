package duke.tasks;

import java.io.IOException;

import duke.utils.DateProcessor;

/**
 * Inherited Task for Event
 */
public class Event extends Task {
    protected String type = "E";


    /**
     * Initialized an Event Task
     * Initialized task description and due time
     *
     * @param description Description of task
     * @param due         UNIX timestamp of start and due time
     */
    public Event(String description, long[] due) {
        super(description);

        if (due.length == 0) {
            this.due = 0;
            return;
        }

        this.due = due[0];
        if (due.length != 1) {
            this.to = due[1];
        }
    }

    @Override
    public String toString() {
        try {
            initLocale();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String label = String.format("\t%s", ui.getEventLabel());

        if (this.due == 0) {
            return label + super.toString();
        }

        String overdue = "";

        if (this.to == 0) {
            if (DateProcessor.getTimeNow() >= this.due && !this.isDone) {
                overdue = " - Overdue";
            }
            return label + super.toString()
                    + " (" + d.unixToString(this.due) + ")" + overdue;
        }

        if (DateProcessor.getTimeNow() >= this.to && !this.isDone) {
            overdue = " - Overdue";
        }
        return label + super.toString()
                + " (" + d.unixToString(this.due)
                + " ~ " + d.unixToString(this.to) + ")" + overdue;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getIsDone() {
        return this.isDone ? "1" : "0";
    }
}
