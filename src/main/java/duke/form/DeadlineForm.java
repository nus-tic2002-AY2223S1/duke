package duke.form;

import java.time.LocalDateTime;

/**
 * Form stores the parsed parameters from console.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class DeadlineForm extends TodoForm {

    /**
     * Deadline time.
     */
    private LocalDateTime by;

    /**
     * Multi args constructor.
     *
     * @param metaData: Raw input from user.
     * @param command: Command name.
     * @param description: Command description.
     */
    public DeadlineForm(String metaData, String command, String description) {
        super(metaData, command, description);
    }

    /**
     * Returns deadline time.
     *
     * @return Deadline time instance.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Sets deadline time.
     *
     * @param by: Target time.
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }
}
