package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTodo(description);
            ui.printBox("Added '" + tasks.getTask(tasks.size()) + "' into the list.");
        } catch(ArrayIndexOutOfBoundsException e) {
            ui.printBox("O_o Error. The description of Todo cannot be empty. Try again. ");
        }
    }
}
