package commands;

import java.io.IOException;
import tasks.TaskList;
import userinterface.Ui;
import storage.Storage;

public class ByeCommand extends Command {

    private static boolean exit = false;

    public static boolean exit() {
        return exit;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveFile(tasks.getList());
        } catch (IOException e) {
            ui.printBox("O_o Error. File could not be found. Try again.");
        }
        ui.goodbye();
        exit = true;
    }
}

