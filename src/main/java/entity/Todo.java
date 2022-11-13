package entity;

import entity.Task;

public class Todo extends Task {

    /**
     * To-do tasks constructor
     *
     * @param description inputs by user
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Parse To-do object to string
     *
     * @return a string to be saved to text file
     */
    @Override
    public String toFile() {
        return "T" + " | " + getStatus() + " | "  + getTags() + " | " + description + "\n";
    }

    /**
     * Parse To-do object to string
     *
     * @return a string of To-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
