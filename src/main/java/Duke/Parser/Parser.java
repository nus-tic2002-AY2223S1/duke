package Duke.Parser;
import Duke.Exception.DukeException;

public class Parser {
    public static String command;
    public static String listIdx;

    public Parser(String userInput) throws DukeException {
        boolean oneWord = false;
        String[] userInputArray = userInput.split(" ",2);
        command = userInputArray[0].toLowerCase();
        if(command.equals("list") || command.equals("bye")) {
            oneWord = true;
        }
        switch(command.toLowerCase()) {
            case "done":
            case "delete":
                listIdx = userInputArray[1];
                break;
        }
    }

    public String getCommand() {
        return command;
    }
}