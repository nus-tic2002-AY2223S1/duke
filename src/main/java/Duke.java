import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {
    public static Task[] arr = {};
    public static ArrayList<Task> arrayList = new ArrayList<>(Arrays.asList(arr));
    static Ui ui = new Ui();

    public static void main(String[] args) throws DukeException {
        ui.sendWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] in = ui.readIn();
                Cmd c = Parser.parse(in);
                c.run(arrayList,ui);
                isExit = c.isExit();
            } catch (DukeException e) {
//                ui.showError(e.getMessage());
            }
        }
    }
    }
