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
     * Return a string including status icon, description of todo task
     *
     * @return Both status icon and description of todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return a string including both the status icon and description of todo task in format to be saved in task.txt
     *
     * @return Status icon and description of todo task in a string
     */
    @Override
    public String stringToOutput() {
        return "T" + super.stringToOutput();
    }
}