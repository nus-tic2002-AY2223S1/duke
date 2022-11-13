package Duke;

import Duke.Commands.Command;
import Duke.Common.Messages;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.UI.Ui;

import java.io.IOException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     *  To load up the data from a file path
     *
     * @param filePath to load data from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException io) {
            ui.printMessage(io.getMessage());
            exit();
        }
        run();
    }

    /**
     *  Show welcome message and run the program until termination
     */
    public void run() {
        ui.showWelcomeMessage();
        loopTillExit();
        exit();
    }

    /**
     *  To continuously run the program and save data to storage until exit command
     */
    private void loopTillExit() {
        String output;
        do {
            output = "";
            String userCommand = ui.getUserCommand();
            try {
                Parser p = new Parser();
                Command c = p.parseCommand(userCommand);
                if (c != null){
                    output = c.execute(storage, taskList);
                    ui.showResultToUser(output);
                    storage.saveList(taskList.getListOfTask());
                }
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
            }
        } while (!output.equals(Messages.MESSAGE_GOODBYE));
    }

    /**
     *  To terminate the program
     */
    public void exit() {
        System.exit(0);
    }

    /**
     *  main to initiate the whole program
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
