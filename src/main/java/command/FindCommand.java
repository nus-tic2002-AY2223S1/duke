package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public String keyword;

    /**
     * Find command constructor
     *
     * @param keyword keyword to search among the tasklist
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Given a keyword, this method will search through myTaskList and find tasks that contains the keyword.
     * These task would be shown to user.
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filterList = new ArrayList<>();
        if (tasks.myTaskList.size() == 0) {
            ui.showMessage("There is no task currently");
        } else {
            for (Task task : tasks.myTaskList) {
                if (task.getDescription().contains(this.keyword)) {
                    filterList.add(task);
                }
            }
        }
        if (filterList.size() == 0) {
            ui.showMessage("There is no matching task matching the keyword.");
        } else {
            ui.showMessage("Here are the matching tasks in your lists:");
            for (int count = 0; count < filterList.size(); count++) {
                ui.showMessage((count + 1) + "." + filterList.get(count).toString());
            }
        }
    }
}
