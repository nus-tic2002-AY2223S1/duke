package entity;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Task {
    
    private String description = "";
    private boolean isDone = false;
    
    /**
     * Task Constructor
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description.trim();
    }
    
    /**
     * Task Constructor
     *
     * @param description task description
     * @param isDone      indicate if the task is marked as done
     */
    public Task(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }
    
    /**
     * Return task description
     *
     * @return description variable
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Set task description
     *
     * @param description description that user inputted
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Toggle task isDone status
     */
    public void updateStatus() {
        this.isDone = !isDone;
    }
    
    /**
     * Return task status
     *
     * @return isDone variable
     */
    public boolean isDone() {
        return isDone;
    }
    
    /**
     * Return task status indicator
     *
     * @return status icon of task
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
    
    /**
     * Return task type indicator
     *
     * @return type icon of task
     */
    public String getTypeIcon() {
        return " ";
    }
    
    /**
     * Return task detail in string type with specific format
     *
     * @return task details
     */
    public String getDetails() {
        return String.format("[%s]%s %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
    
    /**
     * Return task details for saving locally
     *
     * @return task details with saving format
     */
    public String getSavingFormatDetails() {
        return String.format("%s | %d | %s\n", getTypeIcon(), isDone ? 1 : 0, getDescription());
    }
    
    /**
     * A Comparator that can sort task list by name in ascending order
     */
    public static Comparator<Task> descriptionComparator = new Comparator<Task>() {
        
        public int compare(Task t1, Task t2) {
            String description1 = t1.getDescription().toUpperCase();
            String description2 = t2.getDescription().toUpperCase();
            
            //ascending order
            return description1.compareTo(description2);
            
            //descending order
            //return description2.compareTo(description1);
        }
    };
    
    /**
     * A Comparator that can sort task list by datetime in ascending order
     */
    public static Comparator<Task> dateTimeComparator = new Comparator<Task>() {
        
        public int compare(Task t1, Task t2) {
            
            String t1DateTime = getTaskDateTime(t1);
            String t2DateTime = getTaskDateTime(t2);
            
            if (t1DateTime == null && t2DateTime != null) {
                return 1;
            } else if (t1DateTime != null && t2DateTime == null) {
                return -1;
            } else if (t1DateTime == null) {
                return descriptionComparator.compare(t1, t2);
            }
            
            //ascending order
            return t1DateTime.compareTo(t2DateTime);
            
            //descending order
            //return t1DateTime.compareTo(t2DateTime);
        }
    };
    
    private static String getTaskDateTime(Task task) {
        
        if (task instanceof Deadline) {
            return ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            return ((Event) task).getTime();
        } else {
            return null;
        }
        
    }
}
