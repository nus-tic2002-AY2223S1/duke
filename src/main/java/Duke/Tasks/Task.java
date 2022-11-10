package Duke.Tasks;

/**
 * Represents a Task. Contains the data of all Duke.Tasks.Task such as Todo, Events and Deadlines.
 */

public class Task {
    protected String description;
    protected boolean isDone = false;


    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        String mark = this.getStatus() ? "[X]" : "[ ]";
        return mark + " " + description;
    }

    public String getDescriptionOnly() {
        return description;
    }

    public boolean getStatus(){
        return isDone;
    }

    private void setStatus(boolean b){
        isDone = b;
    }


    public void markTask(){
        this.setStatus(true);
    }
    public void unmarkTask(){
        this.setStatus(false);
    }

}