package nus.duke;

import java.util.Scanner;
import nus.duke.exceptions.*;
import nus.duke.frontend.*;
import nus.duke.parser.*;

public class Duke {
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    public Duke(String filePath) {
        // nth here;
    }

    public void run() {

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
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
