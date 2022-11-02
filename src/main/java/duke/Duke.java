package duke;

import java.io.FileNotFoundException;

import duke.impl.Cmd;
import duke.impl.Parser;
import duke.impl.Storage;
import duke.impl.Ui;
import duke.tasks.TaskList;
import duke.utils.DukeException;

public class Duke {
    private final Ui ui;
    private TaskList t;
    private final Parser p;

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

    public String getResponse(String input) {
        return inter(input);
    }

    public String[] getWelcome() {
        return ui.sendWelcomeMessage(t);
    }

    private String inter(String s) {
        Cmd c = p.readInText(s).parse();
        return c.run(t.getList());
    }
}
