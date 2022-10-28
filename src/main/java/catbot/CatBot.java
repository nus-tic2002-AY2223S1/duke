package catbot;

import catbot.command.Command;
import catbot.task.TaskList;

import java.io.IOException;

public class CatBot {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public CatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());
        } catch (FileCorruptedException | DuplicatedTaskException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CatBotException c) {
                ui.showError(c.getMessage());
            }  catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new CatBot("data/tasks.txt").run();
    }
}
