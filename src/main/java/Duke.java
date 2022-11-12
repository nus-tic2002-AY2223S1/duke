import java.io.IOException;

import exceptions.InvalidStorageFilePathException;
import parser.Parser;
import storage.Storage;
import tasklist.*;
import ui.UI;

import static ui.ErrorMessages.*;

public class Duke {
    private Storage storage;
    private TaskList newTaskList;
    private UI ui;


    public Duke() throws IOException {
        ui = new UI();
        storage = new Storage(Storage.path);
        try {
            newTaskList = new TaskList(Storage.load());
        }
        catch (Exception e){
            UI.printMessage(LOADING_ERROR);
            newTaskList = new TaskList();
        }
    }

    //@@author nglihui
    private void start() throws InvalidStorageFilePathException, IOException {
        this.ui = new UI();
        ui.printIntro();

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