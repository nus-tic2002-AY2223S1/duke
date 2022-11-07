package manager;

import entity.Command;
import exception.DukeException;
import javafx.application.Platform;
import parser.CommandParser;
import util.CommandType;


public class CommandManager {
    
    private static CommandManager instance;
    
    private static TaskManager taskManager;
    
    /**
     * Create a new instance of CommandManager class
     */
    private static void newInstance() {
        instance = new CommandManager();
        taskManager = TaskManager.getInstance();
    }
    
    /**
     * Return the current CommandManager instance
     * If not exist, create a new instance and return
     *
     * @return current CommandManager instance
     */
    public static CommandManager getInstance() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }
    
    /**
     * Execute user command
     * Return response message if execution is successful
     * Return error message if execution is unsuccessful
     *
     * @param inputArr user input array, size is 2, [first word of command, rest of the command]
     * @return response message or error message
     * @throws DukeException
     */
    public String executeUserInput(String[] inputArr) throws DukeException {
        
        String executionResult = "";
        
        Command cmd = CommandParser.parseStringArrToCommand(inputArr);
        
        switch (CommandType.valueOf(cmd.getCommand().toUpperCase())) {
        case LIST:
            executionResult = taskManager.getTaskListString();
            break;
        case MARK:
        case UNMARK:
            executionResult = taskManager.getUpdateTaskResult(cmd);
            break;
        case DELETE:
            executionResult = taskManager.getDeleteTaskResult(cmd);
            break;
        case BYE:
            //            executionResult = "Bye. Hope to see you again soon!";
            Platform.exit();
            System.exit(0);
            break;
        case FIND:
            executionResult = taskManager.getFindTaskResult(cmd);
            break;
        case SORT:
            executionResult = taskManager.getSortTaskResult(cmd);
            break;
        default:
            executionResult = taskManager.getAddTaskResult(cmd);
            break;
        }
        
        return executionResult;
    }
}
