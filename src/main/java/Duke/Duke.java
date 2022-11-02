package Duke;

import Duke.Interface.Cmd;
import Duke.Interface.Parser;
import Duke.Interface.Storage;
import Duke.Interface.Ui;
import Duke.Tasks.TaskList;
import Duke.Util.DukeException;

import java.io.FileNotFoundException;

public class Duke {
    private final Ui ui;
    private TaskList t;
    private final Parser p;

    public String getResponse(String input) {
        return inter(input);
    }

    public String[] getWelcome() {
        return ui.sendWelcomeMessage(t);
    }

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

    private String inter(String s) {
        try {
            Cmd c = p.readInText(s).parse();
            return c.run(t.getList());
        } catch (DukeException e) {
            ui.sendGenericFatal(e.getMessage());
        }
        return "";
    }
}
