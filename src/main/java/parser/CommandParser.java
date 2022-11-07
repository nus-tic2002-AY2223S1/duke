package parser;

import entity.Command;
import exception.DukeException;
import util.CommandType;
import util.ErrorMessage;

/**
 * @author Yao Liang
 * @created 07/11/2022 - 2:24 pm
 * @projct Duke
 */
public class CommandParser {
    
    /**
     * Convert user input strings to a command object for further execution
     *
     * @param inputArr user input array, size is 2, [first word of command, rest of the command]
     * @return a Command object contains details of user input
     * @throws DukeException
     */
    public static Command parseStringArrToCommand(String[] inputArr) throws DukeException {
        
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
     * @throws DukeException
     */
    private static void setDescriptionDateTime(Command cmd, String content, String splitter) throws DukeException {
        String description = content.substring(0, content.lastIndexOf(splitter));
        String datetime = content.substring(content.lastIndexOf(splitter) + 4);
        if (description.isEmpty()) {
            throw new DukeException(ErrorMessage.ERROR_MESSAGE_DESCRIPTION_EMPTY.toString());
        }
        if (datetime.isEmpty()) {
            throw new DukeException(ErrorMessage.ERROR_MESSAGE_DATETIME_EMPTY.toString());
        }
        
        cmd.setDescription(description);
        cmd.setDatetime(datetime);
    }
    
    /**
     * Set task's description detail to command object
     *
     * @param cmd command object contains user input details
     * @param cmd command object contains user input details
     * @throws DukeException
     */
    private static void setDescription(Command cmd, String content) throws DukeException {
        if (!content.isEmpty()) {
            cmd.setDescription(content);
        } else {
            throw new DukeException(ErrorMessage.ERROR_MESSAGE_DESCRIPTION_EMPTY.toString());
        }
    }
    
    /**
     * Set task's index detail to command object
     *
     * @param cmd command object contains user input details
     * @param cmd command object contains user input details
     */
    private static void setCommandIndex(Command cmd, String content) {
        if (content.matches("[0-9]+")) {
            cmd.setIndex(Integer.parseInt(content) - 1);
        }
    }
    
}
