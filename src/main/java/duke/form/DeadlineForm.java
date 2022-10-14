package duke.form;

import java.time.LocalDateTime;

public class DeadlineForm extends TodoForm {

    private LocalDateTime by;

    public DeadlineForm(String metaData, String command, String description) {
        super(metaData, command, description);
    }

    public DeadlineForm(String metaData, String command, String description, LocalDateTime by) {
        super(metaData, command, description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }
}
