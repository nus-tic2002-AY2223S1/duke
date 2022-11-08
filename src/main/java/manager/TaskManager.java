package manager;

import entity.*;
import exception.DukeException;
import parser.DatetimeParser;
import parser.TaskParser;
import util.CommandType;
import util.ErrorMessage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    
    private static TaskManager instance;
    private static ArrayList<Task> taskList;
    private StorageManager storageManager;
    
    /**
     * TaskManager Constructor
     * Initiates task list, StorageManager
     * Retrieves last saved task information and adds all to task list
     */
    private TaskManager() {
        taskList = new ArrayList<>();
        storageManager = StorageManager.getInstance();
        ArrayList<String> taskInfoList = storageManager.readFromFile();
        taskList.addAll(TaskParser.parseStringsToTasks(taskInfoList));
    }
    
    /**
     * Creates a new instance of TaskManager class
     */
    private static void newInstance() {
        instance = new TaskManager();
    }
    
    /**
     * Returns the current TaskManager instance
     * If not exist, creates a new instance and return
     *
     * @return current TaskManager instance
     */
    public static TaskManager getInstance() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }
    
    /**
     * Returns task information with display format
     *
     * @return task list information to display
     */
    public String getTaskListString() {
        assert taskList != null : "Task list should not be null.";
        
        StringBuilder printStr = new StringBuilder("Here are the tasks in your list: \n");
        if (taskList.size() > 0){
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                printStr.append(String.format("%d.%s\n", i + 1, t.getDetails()));
            }
        }else{
            printStr = new StringBuilder("You do not have any tasks yet.");
        }
        

        return printStr.toString();
    }
    
    /**
     * Takes in a mark or unmark command object and perform related action
     * Returns execution result
     *
     * @param cmd mark or unmark command object
     * @return execution result
     * @throws DukeException
     */
    public String getUpdateTaskResult(Command cmd) throws DukeException {
        assert taskList != null : "Task list should not be null.";
        assert cmd != null : "Command object should not be null.";
        
        String result = "";
        
        if (cmd.getIndex() != -1) {
            int index = cmd.getIndex();
            Task t = taskList.get(index);
            
            if (cmd.getCommand().equals("mark") && t.isDone()) {
                throw new DukeException(ErrorMessage.ERROR_MESSAGE_ALREADY_MARKED.toString());
            } else if (cmd.getCommand().equals("unmark") && !t.isDone()) {
                throw new DukeException(ErrorMessage.ERROR_MESSAGE_ALREADY_UNMARKED.toString());
            } else {
                t.updateStatus();
                if (t.isDone()) {
                    result = "Nice! I've marked this task as done:\n" + t.getDetails();
                } else {
                    result = "OK, I've marked this task as not done yet:\n" + t.getDetails();
                }
                onTaskListChanged();
            }
        }
        
        return result;
    }
    
    /**
     * Takes in an add command object and perform related action
     * Returns execution result
     *
     * @param cmd add command object
     * @return execution result
     * @throws DukeException
     */
    public String getAddTaskResult(Command cmd) throws DukeException {
        assert taskList != null : "Task list should not be null.";
        assert cmd != null : "Command object should not be null.";
        
        String result = "";
        Task task = null;
        
        switch (CommandType.valueOf(cmd.getCommand().toUpperCase())) {
        case ADD:
            task = new Task(cmd.getDescription());
            break;
        case TODO:
            task = new ToDo(cmd.getDescription());
            break;
        case DEADLINE:
            task = new Deadline(cmd.getDescription(), DatetimeParser.parseStringToDateTime(cmd.getDatetime()));
            break;
        case EVENT:
            task = new Event(cmd.getCommand(), DatetimeParser.parseStringToDateTime(cmd.getDatetime()));
            break;
        }
        
        if (task != null) {
            taskList.add(task);
            
            result = "Got it. I've added this task: \n";
            result += task.getDetails();
            result += "\nNow you have " + taskList.size() + " tasks in the list.";
            
            onTaskListChanged();
        }
        
        return result;
    }
    
    /**
     * Takes in a delete command object and perform related action
     * Returns execution result
     *
     * @param cmd delete command object
     * @return execution result
     * @throws DukeException
     */
    public String getDeleteTaskResult(Command cmd) throws DukeException {
        assert taskList != null : "Task list should not be null.";
        assert cmd != null : "Command object should not be null.";
        
        String result = "";
        Task task;
        
        if (cmd.getIndex() == -1) {
            throw new DukeException(ErrorMessage.ERROR_MESSAGE_DELETE_NOT_NUMBER.toString());
        } else if (cmd.getIndex() > taskList.size()) {
            throw new DukeException(ErrorMessage.ERROR_MESSAGE_DELETE_INDEX_OUT_OF_RANGE.toString());
        } else {
            int index = cmd.getIndex();
            task = taskList.get(index);
            taskList.remove(index);
            
            result = "Noted. I've removed this task: \n";
            result += task.getDetails();
            result += "\nNow you have " + taskList.size() + " tasks in the list.";
            onTaskListChanged();
        }
        return result;
    }
    
    /**
     * Takes in a find command object and perform related action
     * Returns execution result
     *
     * @param cmd find command object
     * @return execution result
     */
    public String getFindTaskResult(Command cmd) {
        assert taskList != null : "Task list should not be null.";
        assert cmd != null : "Command object should not be null.";
        
        ArrayList<Task> resultArr = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDetails().contains(cmd.getDescription())) {
                resultArr.add(task);
            }
        }
        
        StringBuilder resultStr = new StringBuilder("Here are the matching tasks in your list: \n");
        if (resultArr.size() > 0){
            for (int i = 0; i < resultArr.size(); i++) {
                Task t = resultArr.get(i);
                resultStr.append(String.format("%d.%s\n", i + 1, t.getDetails()));
            }
        }else{
            resultStr = new StringBuilder("There are no matching tasks.");
        }
      
        return resultStr.toString();
    }
    
    /**
     * Takes in a sort command object and perform related action
     * Returns execution result
     *
     * @param cmd sort command object
     * @return execution result
     */
    public String getSortTaskResult(Command cmd) {
        assert taskList != null : "Task list should not be null.";
        assert cmd != null : "Command object should not be null.";
        
        if (cmd.getDescription().equals("name")) {
            taskList.sort(Task.descriptionComparator);
        } else if (cmd.getDescription().equals("date")) {
            taskList.sort(Task.dateTimeComparator);
        } else {
            return "☹ OOPS!!! The input sorting method is not found.";
        }
        
        onTaskListChanged();
        return "Noted. I've sorted tasks by " + cmd.getDescription() + ".\n" + getTaskListString();
    }
    
    /**
     * Performs save task list to local action
     */
    private void onTaskListChanged() {
        assert taskList != null : "Task list should not be null.";
        assert storageManager != null : "StorageManager should not be null.";
        
        try {
            storageManager.writeToFile(TaskParser.parseTasksToStrings(taskList));
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
}
