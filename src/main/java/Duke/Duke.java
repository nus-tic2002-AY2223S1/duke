package Duke;

import Duke.Commands.Command;
import Duke.Common.Messages;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.UI.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */

public class Duke {

    private Storage storage;
    private TaskList taskList;

    private Ui ui;

    /** Loads up the data from the storage file  */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException io) {
            taskList = new TaskList(new ArrayList<>());
        }
        run();
    }

    /** Prints welcome message and runs the program until termination */
    public void run() {
        ui.showWelcomeMessage();
        loopTillExit();
        exit();
    }

    private void loopTillExit() {
        String output;
        do {
            output = "";
            String userCommand = ui.getUserCommand().toLowerCase();
            try {
                Parser p = new Parser();
                Command c = p.parseCommand(userCommand);
                if (c != null){
                    output = c.execute(storage, taskList);
                    ui.showResultToUser(output);
                }
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
            }
        } while (!output.equals(Messages.MESSAGE_GOODBYE));
    }

    /** Save data and exit application */
    public void exit() {
        storage.saveList(taskList.getListOfTask());
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
