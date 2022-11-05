package duke.tasks;

import java.io.IOException;

/**
 * Inherited Task for Todo
 */
public class Todo extends Task {
    protected String type = "T";

    /**
     * Initialized a Todo Task
     * Initialized task description and due time
     *
     * @param description Description of task
     * @param due         UNIX timestamp of due time
     */
    public Todo(String description, long due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        try {
            initLocale();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String label = String.format("\t%s", ui.getTodoLabel());

        return (this.due == 0
                ? label + super.toString()
                : label + super.toString() + " (" + ui.getHeader() + d.unixToString(this.due) + ")");
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
