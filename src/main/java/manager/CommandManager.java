package manager;

import entity.Command;
import exception.IllegalActionException;
import exception.IllegalContentException;
import javafx.application.Platform;
import util.CommandType;


public class CommandManager {
    
    private static CommandManager instance;
    
    private static TaskManager taskManager;
    
    public static void newInstance() {
        instance = new CommandManager();
        taskManager = TaskManager.getInstance();
    }
    
    public static CommandManager getInstance() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }
    
    
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
    
    private void setDescriptionDateTime(Command cmd, String content, String regex) throws IllegalContentException {
        String description = content.substring(0, content.lastIndexOf(regex));
        String datetime = content.substring(content.lastIndexOf(regex) + 4);
        if (description.isEmpty()) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + cmd.getCommand() + " cannot be empty.");
        }
        if (datetime.isEmpty()) {
            throw new IllegalContentException("☹ OOPS!!! The due date/time of a " + cmd.getCommand() + " cannot be empty.");
        }
        
        cmd.setDescription(description);
        cmd.setDatetime(datetime);
    }
    
    private void setDescription(Command cmd, String content) throws IllegalContentException {
        if (!content.isEmpty()) {
            cmd.setDescription(content);
        } else {
            throw new IllegalContentException("");
        }
    }
    
    private void setCommandIndex(Command cmd, String content) {
        if (content.matches("[0-9]+")) {
            cmd.setIndex(Integer.parseInt(content) - 1);
        }
    }
    
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
