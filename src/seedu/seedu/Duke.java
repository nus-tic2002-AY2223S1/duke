package nusduke;

import nusduke.data.DukeException;
import nusduke.parser.Command;
import nusduke.parser.Parser;
import nusduke.storage.Storage;
import nusduke.tasklist.TaskList;
import nusduke.ui.Ui;


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
