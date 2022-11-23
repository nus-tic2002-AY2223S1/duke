import exceptions.DukeException;
import parser.Command;
import parser.Parser;
import storage.Storage;
import tasklist.Tasklist;
import ui.Ui;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;

    /** Load files */
    public Duke (String filePath) {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);
        try {
            if (Ui.promptFilepath()){
                tasks = new Tasklist(Storage.load());
            } else {
                tasks = new Tasklist();
                System.out.println(Ui.UI_DIVIDER);
            }
        } catch (IOException e) {
            Storage.filePath = "data/duke.txt";
            System.out.println(Ui.ERROR_FILENOTFOUND + "\n" + Ui.UI_DIVIDER);
            tasks = new Tasklist();
        }
    }

    public void run() {
        boolean isExit = false;

        while (!isExit) {
            try {
                /** Capture userinput as a String "fullCommand" */
                String fullCommand = Ui.readCommand();
                /** Parse String fullCommand into Command type "c" */
                Command c = Parser.parse(fullCommand);
                /** Execute the action required against Command c */
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(Ui.ERROR_START);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}