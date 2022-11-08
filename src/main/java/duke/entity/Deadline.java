package duke.entity;

import duke.constant.Constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entity class to represent deadline.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class Deadline extends Task {

    /**
     * Deadline time.
     */
    private LocalDateTime by;

    /**
     * Multi args constructor.
     *
     * @param description: Command description.
     */
    public Deadline(String description) {
        super(description);
        setType(Constant.Task.TYPE_DEADLINE);
    }

    /**
     * Multi args constructor.
     *
     * @param description: Command description.
     * @param by: Deadline of task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        setType(Constant.Task.TYPE_DEADLINE);
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

    /**
     * Returns string representation of class.
     *
     * @return Readable string representation for instance description.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getDisplayTime());
    }

    /**
     * Returns the customised representation of deadline.
     *
     * @return Readable string representation for instance deadline.
     */
    private String getDisplayTime() {
        return by.format(DateTimeFormatter.ofPattern(Constant.Time.DISPLAY_FORMAT));
    }
}
