package duke.form;

import java.time.LocalDateTime;

/**
 * Form stores the parsed parameters from console.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class EventForm extends TodoForm {

    /**
     * Start time of event.
     */
    private LocalDateTime startTime;

    /**
     * End time of event.
     */
    private LocalDateTime endTime;

    /**
     * Multi args constructor.
     *
     * @param metaData: Raw input from user.
     * @param command: Command name.
     * @param description: Command description.
     */
    public EventForm(String metaData, String command, String description) {
        super(metaData, command, description);
    }

    /**
     * Get start time.
     *
     * @return Start time instance.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Set start time.
     *
     * @param startTime: Target start time.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Get end time.
     *
     * @return End time instance.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Set end time.
     *
     * @param endTime: Target end time.
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
