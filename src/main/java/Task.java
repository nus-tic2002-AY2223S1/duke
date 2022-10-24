public class Task {

    protected String TaskName;
    private boolean MarkAsDone;

    //constructor
    public Task(String taskName) {
        this.TaskName = taskName;
        this.MarkAsDone = false;
    }

    public String getTaskName() {
        return TaskName;
    }

    public String isMarkAsDone() {
        if (MarkAsDone) {
            return "X";
        }
        else {
            return " ";
        }
    }
    public void setMarkAsDone(){
        this.MarkAsDone = true;
    }

    public void setMarkNotDone(){
        this.MarkAsDone = false;
    }

    public String toString() {
        return "[" + isMarkAsDone() + "]" + TaskName;
    }
}
