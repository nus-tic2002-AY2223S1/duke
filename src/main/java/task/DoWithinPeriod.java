package task;

public class DoWithinPeriod extends Task {

    public String startPeriodDate;
    public String endPeriodDate;

    /**
     * DoWithinPeriod task constructor
     *
     * @param description     task descriptions
     * @param startPeriodDate task period start date
     * @param endPeriodDate   task period end date
     */
    public DoWithinPeriod(String description, String startPeriodDate, String endPeriodDate) {
        super(description);
        this.startPeriodDate = startPeriodDate;
        this.endPeriodDate = endPeriodDate;
    }

    /**
     * Return a string including status icon, description, period start & end date and priority of task
     *
     * @return Status icon, description and period start & end date and priority of task in a string
     */
    @Override
    public String toString() {
        return "[DWP]" + super.toString() + " (between " + this.startPeriodDate + " and " + this.endPeriodDate + ")" + " [" + this.getPriority() + "]";
    }

    /**
     * Return a string including status icon, description and period start & end date and priority of task in format to be saved in task.txt
     *
     * @return Status icon, description and period start & end date and priority of task in a string
     */
    @Override
    public String stringToOutput() {
        return "DWP" + super.stringToOutput() + " | " + this.startPeriodDate + " | " + this.endPeriodDate + " | " + super.getPriority();
    }
}
