import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import command.*;
import entities.Storage;
import entities.Task;
import entities.TaskList;
import exception.DukeException;
import parser.Parser;
import ui.Ui;
import utils.DukeUtils;

import static command.Command.*;

public class Duke {

    private static final Scanner s = new Scanner(System.in);
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Parser.parser(fullCommand);

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
