package duke;

import java.io.FileNotFoundException;

import static duke.orm.Database.logDuke;
import static duke.orm.Database.logUser;

import duke.impl.Cmd;
import duke.impl.Parser;
import duke.impl.Storage;
import duke.impl.Ui;
import duke.orm.Database;
import duke.tasks.TaskList;

/**
 * Main class of Duke
 **/
public class Duke {
    private Database db;
    private final Ui ui;
    private TaskList t;
    private final Parser p;

    /**
     * Initialize IO, Parser, and restore previous records. (If any)
     *
     * @param path File path to read previous records from.
     */
    public Duke(String path) {
        db = new Database();
        db.createTable();
        ui = new Ui();
        p = new Parser();
        Storage s = new Storage(path);
        try {
            t = new TaskList(s.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            t = new TaskList();
        }
    }

    /**
     * Initialize IO, Parser, and restore previous records. (If any)
     *
     * @param input Interface between GUI and Duke
     * @return Result from Duke interface
     */
    public String getResponse(String input) {
        return inter(input);
    }

    /**
     * Welcome message to GUI.
     *
     * @return Welcome message text
     */
    public String[] getWelcome(boolean shouldList) {
        return ui.sendWelcomeMessage(t, shouldList);
    }

    /**
     * Entry point to Duke Implementation
     *
     * @param s User input command
     */
    private String inter(String s) {
        logUser(s);
        Cmd c = p.readInText(s).parse();
        String resp = c.run(t.getList());
        logDuke(resp);
        return resp;
    }
}
