package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class EventCommand extends Command {
    private String description;
    private String due;

    public EventCommand(String description, String due) {
        this.description = description;
        this.due = due;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addEvent(description, due);
            ui.printBox("Added '" + tasks.getTask(tasks.size()) + "' into the list.");
        } catch(ArrayIndexOutOfBoundsException e) {
            ui.printBox("O_o Error. The description of Event cannot be empty. Try again. ");
        }
    }
}
