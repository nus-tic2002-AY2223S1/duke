import java.io.IOException;

import exceptions.InvalidStorageFilePathException;
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
    private void start(String[] launchArgs) throws InvalidStorageFilePathException, IOException {
        this.ui = new UI();

        ui.printIntro();

        while (true) {
                String input = UI.getUserCommand();
                assert input != null : "User should not input null.";
                String[] inputSplit = input.split(" ");

                // enter bye to end chat
                if (input.equals("bye"))
                    exit();

                // to list all items
                else if (input.equals("list"))
                    newTaskList.listTask(inputSplit, input);

                // mark items
                else if (input.startsWith("mark"))
                    newTaskList.markTask(inputSplit, input);

                // unmarked items
                else if (input.startsWith("unmark"))
                    newTaskList.unmarkTask(inputSplit, input);

                // to do task
                else if (input.startsWith("todo"))
                    newTaskList.todoTask(input, inputSplit);

                // deadline task
                else if (input.startsWith("deadline"))
                    newTaskList.deadlineTask(input, inputSplit);

                // event task
                else if (input.startsWith("event"))
                    newTaskList.eventTask(input, inputSplit);

                // delete task
                else if (input.startsWith("delete"))
                    newTaskList.deleteTask(input, inputSplit);

                // view scheduled task for the date
                else if (input.startsWith("schedule for"))
                    newTaskList.scheduleTask(input, inputSplit);

                else if (input.startsWith("find"))
                    newTaskList.findTask(input, inputSplit);

                else if (input.startsWith("fixed"))
                    newTaskList.fixedDurationTasks(input,inputSplit);

                // prompt user to enter valid input
                else
                    UI.printStandardError();

                // to actively store task list
                Storage.save(newTaskList.taskList);
            }
        }

    //    Prints the Goodbye message and exits.
    private void exit() {
        ui.printBye();
        System.exit(0);
    }

    public static void main(String... launchArgs) throws InvalidStorageFilePathException, IOException {
        new Duke().start(launchArgs);
    }
}