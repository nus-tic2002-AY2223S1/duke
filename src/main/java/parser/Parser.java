package parser;

import commands.*;

public class Parser {
//    public static parse(String fullCommand) {
//        return;
//    }

    public static Command parseCommand(String userInput){
        String task = userInput.split(" ")[0];

        if (task.equals(ExitCommand.COMMAND_WORD) )
            return new ExitCommand();

        else if (task.equals(ListCommand.COMMAND_WORD))
            return new ListCommand();

        else if (task.equals(TodoCommand.COMMAND_WORD))
            return new TodoCommand(userInput.substring(5));

        else if (task.equals(DeadlineCommand.COMMAND_WORD))
            return new DeadlineCommand(userInput.substring(9));

        else if (task.equals(EventCommand.COMMAND_WORD))
            return new EventCommand(userInput.substring(6));

        else
            return new ListCommand();
    }


}
