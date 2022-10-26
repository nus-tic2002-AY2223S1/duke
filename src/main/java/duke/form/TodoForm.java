package duke.form;

/**
 * Form stores the parsed parameters from console.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class TodoForm extends Form {

    /**
     * Description of task.
     */
    private String description;

    /**
     * Multi args constructor.
     *
     * @param metaData: Raw input from user.
     * @param command: Command name.
     * @param description: Command description.
     */
    public TodoForm(String metaData, String command, String description) {
        super(metaData, command);
        this.description = description;
    }

    /**
     * Get description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.
     *
     * @param description: Description of task.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
