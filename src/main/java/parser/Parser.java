
package parser;

import taskList.Deadline;
import taskList.Task;
import taskList.TaskList;
import taskList.Todo;
import ui.UI;

import java.nio.channels.ScatteringByteChannel;

import static ui.ErrorMessages.INVALID_TODO_INPUT;
import static ui.ErrorMessages.TASK_NUMBER_OOB;
import static ui.UI.printError;
import static ui.UI.printLine;

public class Parser {
    private static UI ui;


    Parser() {

    }

    protected static void parse(String userInput) {
    }

    public static Todo parseTodoInput(String input) {
        String todoTask = input.substring(5);
        Todo newTodoTask = new Todo(todoTask);
        return newTodoTask;
    }
}
//    public static Deadline parseDeadlineInput(String input, String[] inputSplit){
//        try {
//            if (inputSplit[0].length() == 4) {
//                String[] deadlineItemSplit = (input.substring(9)).split(" /by ");
//                String deadlineTask = deadlineItemSplit[0];
//                String deadlineBy = deadlineItemSplit[1];
//                Deadline newDeadlineTask = new Deadline(deadlineTask, deadlineBy);
//            return newDeadlineTask;
//        } else
//            throw new ArrayIndexOutOfBoundsException(TASK_NUMBER_OOB);
//    }
//        catch (NullPointerException e){
//        printError("NULLPOINTER");
//        throw new NullPointerException("NULL POINTER");
//    }

//
//    }
//
//
//}

//import commands.*;
//
//public class Parser {
//    public static parse(String fullCommand) {
//        return;
//    }
//
//    public static Command parseCommand(String userInput){
//        String task = userInput.split(" ")[0];
//
//        if (task.equals(ExitCommand.COMMAND_WORD) )
//            return new ExitCommand();
//
//        else if (task.equals(ListCommand.COMMAND_WORD))
//            return new ListCommand();
//
//        else if (task.equals(TodoCommand.COMMAND_WORD))
//            return new TodoCommand(userInput.substring(5));
//
//        else if (task.equals(DeadlineCommand.COMMAND_WORD))
//            return new DeadlineCommand(userInput.substring(9));
//
//        else if (task.equals(EventCommand.COMMAND_WORD))
//            return new EventCommand(userInput.substring(6));
//
//        else
//            return new ListCommand();
//    }
