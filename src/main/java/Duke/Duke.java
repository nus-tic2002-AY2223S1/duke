package Duke;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.Task;
import Duke.TaskList.Tasklist;
import Duke.Ui.Ui;

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static Tasklist tl;

    public Duke (String path) throws DukeException {
        ui = new Ui();
        ui.hello();

        try {
            storage = new Storage(path);
            tl = new Tasklist(storage.load());
        } catch (DukeException e){
            tl = new Tasklist();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry the file cannot be located.");
        } catch (IOException e) {
            throw new DukeException("Unable to create tasks.txt");
        }
    }

    public void run() {
        String userInput;
        boolean end = false;

        while (!end) {
            try {
                userInput = Ui.getUserInput().nextLine();
                Ui.line();
                Ui.input(tl, userInput);

                if(userInput.equalsIgnoreCase("bye")) {
                    end = true;
                }
            } catch(DukeException e) {
                Ui.error(e.getMessage());
            } finally {
                Ui.line();
            }
        }
    }

    public static void main(String[] arg) throws DukeException {
        new Duke("tasks.txt").run();
    }
}
