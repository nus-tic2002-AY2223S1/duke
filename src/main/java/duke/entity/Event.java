package duke.entity;

import duke.constant.Constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entity class to represent event.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class Event extends Task {

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
     * @param description: Command description.
     */
    public Event(String description) {
        super(description);
        setType(Constant.Task.TYPE_EVENT);
    }

    /**
     * Multi args constructor.
     *
     * @param description: Command description.
     * @param startTime: Start time of event.
     * @param endTime: End time of event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        setType(Constant.Task.TYPE_EVENT);
    }

    /**
     * Returns start time.
     *
     * @return Start time instance.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime: Target start time.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns end time.
     *
     * @return End time instance.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime: Target end time.
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns string representation of class.
     *
     * @return Readable string representation for instance description.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), getDisplayTime());
    }

    /**
     * Returns customised representation of deadline.
     *
     * @return Readable string representation for instance deadline.
     */
    private String getDisplayTime() {
        String begin = startTime.format(DateTimeFormatter.ofPattern(Constant.Time.DISPLAY_FORMAT));
        String end = endTime.format(DateTimeFormatter.ofPattern(Constant.Time.DISPLAY_FORMAT));
        return String.format("%s - %s", begin, end);
    }
}
