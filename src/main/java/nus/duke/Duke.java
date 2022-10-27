package nus.duke;

import nus.duke.data.DukeException;
import nus.duke.parser.Command;
import nus.duke.parser.*;
import nus.duke.storage.Storage;
import nus.duke.tasklist.TaskList;
import nus.duke.ui.Ui;


/**
 * Entry point of the Address nus-Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    /** Version info of the program. */
    public static final String VERSION = "Duke Level 7 - Version 1.0";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /** Initiation of Duke.  */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /** Runs the program until termination.  */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}
