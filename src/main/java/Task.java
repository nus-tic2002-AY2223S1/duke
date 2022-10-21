public class Task {

    public enum Type {
        TODO, EVENT, DEADLINE
    }
    private String TaskName;
    private boolean MarkAsDone;

    private int Index;
    private Type TaskType;

    //constructor
    public Task(String taskName) {
        this.TaskName = taskName;
        this.MarkAsDone = false;
        this.TaskType = Type.TODO;
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

    public void setTypeTodo() { this.TaskType = Type.TODO; }

    public void setTypeEvent() { this.TaskType = Type.EVENT; }

    public void setTypeDeadline() { this.TaskType = Type.DEADLINE; }



}
