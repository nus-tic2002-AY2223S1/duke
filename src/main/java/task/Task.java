package task;

public abstract class Task {

    protected String TaskName;
    private boolean isMarkAsDone;

    //constructor
    public Task(String taskName) {
        this.TaskName = taskName;
        this.isMarkAsDone = false;
    }

    public String getTaskName() {
        return TaskName;
    }

    public String isMarkAsDone() {
        if (isMarkAsDone) {
            return "X";
        }
        else {
            return " ";
        }
    }
    public void setMarkAsDone(){
        this.isMarkAsDone = true;
    }

    public void setMarkNotDone(){
        this.isMarkAsDone = false;
    }

    public abstract String toString();
}
