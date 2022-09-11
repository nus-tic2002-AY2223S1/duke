public class Task {
    private String TaskName;
    private boolean MarkAsDone;

    private int Index;

    //constructor
    public Task(String taskName) {
        this.TaskName = taskName;
        this.MarkAsDone = false;
    }

    public String getTaskName() {
        return TaskName;
    }

    public boolean isMarkAsDone() {
        return MarkAsDone;
    }

    public void setMarkAsDone(){
        this.MarkAsDone = true;
    }

    public void setMarkNotDone(){
        this.MarkAsDone = false;
    }


}
