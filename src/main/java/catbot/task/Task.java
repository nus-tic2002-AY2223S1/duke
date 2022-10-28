package catbot.task;

import catbot.TaskType;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getTaskName() {
        return name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setToUndone(){
        this.isDone = false;
    }

    public void setToDone(){
        this.isDone = true;
    }

    public TaskType getTaskType(){
        return TaskType.NONE;
    }

    @Override
    public String toString() {
        String isCompleted;

        if(this.getIsDone()){
            isCompleted = "X";
        }
        else {
            isCompleted = " ";
        }
        return ("[" + isCompleted + "] " + this.getTaskName() );
    }
}
