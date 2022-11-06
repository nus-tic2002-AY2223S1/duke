import Command.Command;
import exception.CommandInvalidException;
import exception.LackDetailsException;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;
import exception.UnknownException;

import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
//    private TaskList tasks = TaskList.getInstance();
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList (storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c =  new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LackDetailsException | UnknownException | CommandInvalidException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


