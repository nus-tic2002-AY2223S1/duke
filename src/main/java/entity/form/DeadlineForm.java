package entity.form;

public class TodoForm extends Form {

    private String taskName;

    public TodoForm(String metaData, String command, String taskName) {
        super(metaData, command);
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
