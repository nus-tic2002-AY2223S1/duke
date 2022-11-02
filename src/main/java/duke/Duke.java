package duke;

import java.io.FileNotFoundException;

import duke.impl.Cmd;
import duke.impl.Parser;
import duke.impl.Storage;
import duke.impl.Ui;
import duke.tasks.TaskList;

/**
 * Main class of Duke
 **/
public class Duke {
    private final Ui ui;
    private TaskList t;
    private final Parser p;

    /**
     * Initialize IO, Parser, and restore previous records. (If any)
     *
     * @param path File path to read previous records from.
     */
    public Duke(String path) {
        ui = new Ui();
        p = new Parser();
        Storage s = new Storage(path);
        try {
            t = new TaskList(s.load());
        } catch (FileNotFoundException e) {
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
    public String[] getWelcome() {
        return ui.sendWelcomeMessage(t);
    }

    /**
     * Entry point to Duke Implementation
     *
     * @param s User input command
     */
    private String inter(String s) {
        Cmd c = p.readInText(s).parse();
        return c.run(t.getList());
    }
}
