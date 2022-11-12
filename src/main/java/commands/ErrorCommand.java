package commands;

import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class ErrorCommand extends Command {
    private String type;

    public ErrorCommand(String type) {
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (type) {
            case "invalid":
                ui.printBox("O_o Error. I don't understand what that means. Try again.");
                break;
            case "missing":
                ui.printBox("O_o Error. The description for Todo, Deadline and Event cannot be missing. Try again.");
                break;
        }
    }
}
