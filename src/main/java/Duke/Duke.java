package Duke;
import Interface.Cmd;
import Interface.Parser;
import Interface.Ui;
import Util.DukeException;
import java.io.FileNotFoundException;

public class Duke  {
    private Ui ui;
    private TaskList t;
    private Parser p;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return inter(input);
    }
    String[] getWelcome() {
        return ui.sendWelcomeMessage(t);
    }
    public Duke(String path){
        ui = new Ui();
        p = new Parser();
        Storage s = new Storage(path);
        try{
            t = new TaskList(s.load());
        }catch (FileNotFoundException e){
            t = new TaskList();
        }
    }
    public static void main(String[] args) {
        new Duke("save/output.txt").run();
    }

    public void run() {
        ui.sendWelcomeMessage(t);
        boolean isExit = false;
        while (!isExit) {
            try {
                Cmd c = p.readIn().parse();
                c.run(t.getList());
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.sendGenericFatal(e.getMessage());
            }
        }
    }

    public String inter(String s){
        try {
            Cmd c = p.readInText(s).parse();
            return c.run(t.getList());
        } catch (DukeException e) {
            ui.sendGenericFatal(e.getMessage());
        }
        return "";
    }
    }
