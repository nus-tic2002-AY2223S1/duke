package seedu;

import seedu.nusduke.data.DukeException;
import seedu.nusduke.parser.Command;
import seedu.nusduke.parser.Parser;
import seedu.nusduke.storage.Storage;
import seedu.nusduke.tasklist.TaskList;
import seedu.nusduke.ui.Ui;


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
