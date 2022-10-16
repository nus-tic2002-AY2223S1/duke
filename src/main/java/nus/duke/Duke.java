package nus.duke;

import java.util.Scanner;

import nus.duke.exceptions.*;
import nus.duke.frontend.*;
import nus.duke.parser.*;
import nus.duke.storage.*;
import nus.duke.tasklist.*;
import java.io.FileNotFoundException;  // Import this class to handle errors


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException fnfe) {
            ui.showHarddiskCreationMessage();
            storage.createHardDiskFile(filePath);
        }
    }

    public void run() {
        ui = new Ui();
        parser = new Parser();

        ui.awakeDobby();
        Scanner s = new Scanner(System.in);
        boolean terminateDobby = false;
        do{
            String userInput = ui.getUserInput(s);
            try {
                terminateDobby = parser.parse(userInput);
            } catch (WrongInputSyntaxException wise){
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-( Please use a command from the command menu");
                ui.printCommandMenu();
            } catch (EmptyTaskException ete){
                System.out.println("OOPS!!! The description of a TODO/DEADLINE/EVENT task cannot be empty.");
            }
        }while(terminateDobby != true);
        storage.saveTasks();
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

}
