package form;

public class TodoForm extends Form {

    private String description;

    public TodoForm(String metaData, String command, String description) {
        super(metaData, command);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
