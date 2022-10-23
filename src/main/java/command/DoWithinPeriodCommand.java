package command;

import storage.Storage;
import task.DoWithinPeriod;
import task.TaskList;
import ui.Ui;

public class DoWithinPeriodCommand extends Command {

    public String description;
    public String startPeriodDate;
    public String endPeriodDate;

    /**
     * DoWithinPeriod command constructor
     *
     * @param description     task descriptions
     * @param startPeriodDate task deadline
     * @param endPeriodDate   task deadline
     */
    public DoWithinPeriodCommand(String description, String startPeriodDate, String endPeriodDate) {
        this.description = description;
        this.startPeriodDate = startPeriodDate;
        this.endPeriodDate = endPeriodDate;
    }

    /**
     * Given task description, period start and end date, this method will add a new doWithinPeriod task in the task list.
     * After adding the task, messages will be shown to user
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Got it. I've added this task:");
        tasks.myTaskList.add(new DoWithinPeriod(description, startPeriodDate, endPeriodDate));
        ui.showMessage(tasks.myTaskList.get(tasks.myTaskList.size() - 1).toString());
        ui.showMessage("Now you have " + (tasks.myTaskList.size()) + " tasks in the list.");
    }
}
