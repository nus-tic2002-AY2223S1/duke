package manager;

import entity.Command;
import exception.IllegalActionException;
import exception.IllegalContentException;
import javafx.application.Platform;
import util.CommandType;


public class CommandManager {
    
    private static CommandManager instance;
    
    private static TaskManager taskManager;
    
    /**
     * Create a new instance of CommandManager class
     */
    public static void newInstance() {
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
     * Convert user input strings to a command object for further execution
     *
     * @param inputArr user input array, size is 2, [first word of command, rest of the command]
     * @return a Command object contains details of user input
     * @throws IllegalContentException
     */
    private Command stringToCommandObj(String[] inputArr) throws IllegalContentException {
        
        Command cmd = new Command();
        cmd.setCommand(inputArr[0]);
        
        if (inputArr.length < 2) {
            return cmd;
        }
        
        switch (CommandType.valueOf(cmd.getCommand().toUpperCase())) {
        case MARK:
        case UNMARK:
        case DELETE:
            // set index
            setCommandIndex(cmd, inputArr[1]);
            break;
        case ADD:
        case TODO:
        case FIND:
            //set description
            setDescription(cmd, inputArr[1]);
            break;
        case DEADLINE:
            //set description + datetime
            setDescriptionDateTime(cmd, inputArr[1], "/by");
            break;
        case EVENT:
            //set description + datetime
            setDescriptionDateTime(cmd, inputArr[1], "/at");
            break;
        case SORT:
            //set sort category
            setDescription(cmd, inputArr[1].substring(inputArr[1].lastIndexOf("by") + 3));
            break;
        }
        return cmd;
    }
    
    /**
     * Set task's description and datetime details to command object
     *
     * @param cmd      command object contains user input details
     * @param content  user input not including the first word
     * @param splitter splitter of task description and datetime
     * @throws IllegalContentException
     */
    private void setDescriptionDateTime(Command cmd, String content, String splitter) throws IllegalContentException {
        String description = content.substring(0, content.lastIndexOf(splitter));
        String datetime = content.substring(content.lastIndexOf(splitter) + 4);
        if (description.isEmpty()) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + cmd.getCommand() + " cannot be empty.");
        }
        if (datetime.isEmpty()) {
            throw new IllegalContentException("☹ OOPS!!! The due date/time of a " + cmd.getCommand() + " cannot be empty.");
        }
        
        cmd.setDescription(description);
        cmd.setDatetime(datetime);
    }
    
    /**
     * Set task's description detail to command object
     *
     * @param cmd command object contains user input details
     * @param cmd command object contains user input details
     * @throws IllegalContentException
     */
    private void setDescription(Command cmd, String content) throws IllegalContentException {
        if (!content.isEmpty()) {
            cmd.setDescription(content);
        } else {
            throw new IllegalContentException("");
        }
    }
    
    /**
     * Set task's index detail to command object
     *
     * @param cmd command object contains user input details
     * @param cmd command object contains user input details
     */
    private void setCommandIndex(Command cmd, String content) {
        if (content.matches("[0-9]+")) {
            cmd.setIndex(Integer.parseInt(content) - 1);
        }
    }
    
    /**
     * Execute user command
     * Return response message if execution is successful
     * Return error message if execution is unsuccessful
     *
     * @param inputArr user input array, size is 2, [first word of command, rest of the command]
     * @return response message or error message
     * @throws IllegalActionException
     * @throws IllegalContentException
     */
    public String executeUserInput(String[] inputArr) throws IllegalActionException, IllegalContentException {
        
        String executionResult = "";
        
        Command cmd = stringToCommandObj(inputArr);
        
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
