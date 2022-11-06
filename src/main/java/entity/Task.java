package entity;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Task {
    
    private String description = "";
    private boolean isDone = false;
    
    public Task(String name) {
        this.description = name.trim();
    }
    
    public Task(String name, boolean isDone) {
        this.description = name.trim();
        this.isDone = isDone;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void updateStatus() {
        this.isDone = !isDone;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
    
    public String getTypeIcon() {
        return " ";
    }
    
    public String getDetails() {
        return String.format("[%s]%s %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
    
    public String getSavingFormatDetails() {
        return String.format("%s | %d | %s\n", getTypeIcon(), isDone ? 1 : 0, getDescription());
    }
    
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
