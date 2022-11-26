import java.time.LocalDate;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    /**
     * The End date.
     */
    protected final LocalDate endDate;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param endDate     the end date
     */
    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param isDone      the is done
     * @param endDate     the end date
     */
    public Deadline(String description, boolean isDone, LocalDate endDate) {
        super(description, isDone);
        this.endDate = endDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String convertToString() {
        return "deadline " + this.getDescription() + " /by " + this.endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DateFormatter.getFormattedDate(this.endDate) + ")";
    }
}
