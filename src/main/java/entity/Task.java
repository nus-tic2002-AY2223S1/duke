package entity;

import java.util.ArrayList;
import java.util.List;

public class Task {
    protected String description;

    protected List<String> tags = new ArrayList<>();

    protected boolean isDone;

    /**
     * Task object constructor
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTags() {
        return String.join(" ", tags);
    }

    public void addTags(List<String> tags) {
        this.tags.addAll(tags);
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * To write tasks to file
     *
     * @return text to save to file
     */
    public String toFile() {
        return getStatus() + " | " + getTags() + " | " + description + "\n";
    }

    /**
     * Show task status
     *
     * @return 1 as done, 0 as not done
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Show task status icon
     *
     * @return X as done, " " as not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark task as done
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmark task as not done
     */
    public void unmarkTask() {
        this.isDone = false;
    }


    public String toString() {
        return "[" + getStatusIcon() + "] Tags: [" + getTags() + "] " + description;
    }
}
