package entity.form;

import java.time.LocalDateTime;

public class DeadlineForm extends TodoForm {

    private LocalDateTime deadline;

    public DeadlineForm(String metaData, String command, String taskName) {
        super(metaData, command, taskName);
    }

    public DeadlineForm(String metaData, String command, String taskName, LocalDateTime deadline) {
        super(metaData, command, taskName);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
