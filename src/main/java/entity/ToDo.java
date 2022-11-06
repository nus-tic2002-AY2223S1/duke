package entity;

public class ToDo extends Task {
    
    /**
     * ToDo Constructor
     *
     * @param description task description
     */
    public ToDo(String description) {
        super(description);
    }
    
    /**
     * ToDo Constructor
     *
     * @param description task description
     * @param isDone      indicate if the task is marked as done
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    
    /**
     * Return task type indicator
     *
     * @return type icon of todo task
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }
}
