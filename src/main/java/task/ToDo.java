package task;

public class ToDo extends Task {

    /**
     * To do task constructor
     *
     * @param description task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Return a string including status icon, description and priority of todo task
     *
     * @return Status icon, description and priority of todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " [" + this.getPriority() + "]";
    }

    /**
     * Return a string including the status icon, description and priority of todo task in format to be saved in task.txt
     *
     * @return Status icon, description and priority of todo task in a string
     */
    @Override
    public String stringToOutput() {
        return "T" + super.stringToOutput() + " | " + super.getPriority();
    }
}