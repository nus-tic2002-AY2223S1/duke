import exceptions.InvalidStorageFilePathException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

import java.io.IOException;

import static ui.ErrorMessages.LOADING_ERROR;


/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */

public class Duke {
    private final Storage storage;
    private TaskList newTaskList;
    private UI ui;


    public Duke() throws IOException {
        ui = new UI();
        storage = new Storage(Storage.path);
        try {
            newTaskList = new TaskList(Storage.load());
        } catch (Exception e) {
            UI.printMessage(LOADING_ERROR);
            newTaskList = new TaskList();
        }
    }

    //@@author nglihui
    private void start() throws InvalidStorageFilePathException, IOException {
        this.ui = new UI();
        UI.printIntro();

        while (true) {
            String input = UI.getUserCommand();
            assert input != null : "User should not input null.";

            // check input to run task
            Parser.parse(input, newTaskList);

            // to actively store task list
            Storage.save(newTaskList.taskList);
        }
    }

    public static void main(String... launchArgs) throws InvalidStorageFilePathException, IOException {
        new Duke().start();
    }
}