package main;

import exception.DukeException;
import manager.CommandManager;
import util.CommandType;
import util.ErrorMessage;

public class Duke {
    
    public static String[] inputArr; //["action", "content"]
    public static CommandManager commandManager;
    
    /**
     * Duke Constructor
     * Initiate CommandManage instance here
     */
    public Duke() {
        commandManager = CommandManager.getInstance();
    }
    
    /**
     * Validate user input
     * And return exception if not valid
     *
     * @param input user input
     * @throws DukeException
     */
    public void validateUserInput(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(ErrorMessage.ERROR_MESSAGE_EMPTY_CONTENT.toString());
        }
        
        inputArr = input.split(" ", 2);
        inputArr[0] = inputArr[0].toLowerCase();
        if (!CommandType.contains(inputArr[0])) {
            throw new DukeException(ErrorMessage.ERROR_MESSAGE_INVALID_ACTION.toString());
        }
    }
    
    
    /**
     * Takes in user input and execute it as a command
     * Return Duke's response
     *
     * @param input user input
     * @return Duke's response
     */
    public String getResponse(String input) {
        String executionResult = "";
        try {
            validateUserInput(input);
            executionResult = commandManager.executeUserInput(inputArr);
            
        } catch (DukeException e) {
            executionResult = e.getMessage();
        }
        
        return executionResult;
    }
    
    /**
     * Return greeting message from Duke to display on application starts
     *
     * @return Greeting message
     */
    public String getGreetingMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?\n\n" +
                "1. To list all existing tasks, enter \"list\".\n" +
                "2. To add a normal task, enter \"add [task content]\".\n" +
                "3. To add a todo task, enter \"todo [task content]\".\n" +
                "4. To add a deadline task, enter \"deadline [task content] /by [deadline datetime]\".\n" +
                "5. To add a event task, enter \"event [task content] /by [event datetime]\"\n" +
                "6. To mark a task as done, enter \"mark [task index]\".\n" +
                "7. To unmark a task as done, enter \"unmark [task index]\".\n" +
                "8. To sort task list,  enter \"sort by [sorting method]\".\n" +
                "9. To exit chatbot, enter \"bye\".\n" +
                "10. The format of deadline/event datetime could be \"dd-MM-yyyy HHmm\" or \"dd/MM/yyyy HHmm\".\n" +
                "11. The sorting method could be \"name\" or \"date\".";
    }
}
