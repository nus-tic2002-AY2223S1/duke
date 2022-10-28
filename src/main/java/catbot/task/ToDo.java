package catbot.task;

import catbot.TaskType;

public class ToDo extends Task{
    private TaskType taskType = TaskType.TODO;
    public ToDo(String description) throws IllegalArgumentException{
        super(description);
    }

    public ToDo(boolean isDone, String description) throws IllegalArgumentException{
        super(description);
        this.name = description;
        this.isDone = isDone;
        this.taskType = TaskType.TODO;
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
