package commands;
import ui.UI;
public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    public static boolean isExit(Command command) {
        UI.printBye();
        return true; // instanceof returns false if it is null
    }


}
