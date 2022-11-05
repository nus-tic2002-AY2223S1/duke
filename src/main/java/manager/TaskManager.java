package manager;

import entity.*;
import exception.IllegalActionException;
import exception.IllegalContentException;
import parser.Parser;
import util.CommandType;
import util.PrintUtil;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    
    private static TaskManager instance;
    private static ArrayList<Task> taskList;
    private FileManager fileManager;
    
    private TaskManager() {
        taskList = new ArrayList<>();
        fileManager = FileManager.getInstance();
        try {
            convertDetailsToTaskList(fileManager.readFromFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalContentException e) {
            PrintUtil.printErrorMessage(e);
        }
    }
    
    public static void newInstance() {
        instance = new TaskManager();
    }
    
    public static TaskManager getInstance() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }
    
    public String getTaskListString() {
        StringBuilder printStr = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            printStr.append(String.format("\t %d.%s\n", i + 1, t.getDetails()));
        }
        return printStr.toString();
    }
    
    
    public void printFindResult(ArrayList<Task> result) {
        StringBuilder printStr = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < result.size(); i++) {
            Task t = result.get(i);
            printStr.append(String.format("\t %d.%s\n", i + 1, t.getDetails()));
        }
        
        PrintUtil.printWithIndentation(printStr.toString());
    }
    
    public String getUpdateTaskResult(Command cmd) throws IllegalActionException {
        
        String result = "";
        
        if (cmd.getIndex() != -1) {
            int index = cmd.getIndex();
            Task t = taskList.get(index);
            
            if (cmd.getCommand().equals("mark") && t.isDone()) {
                throw new IllegalActionException("☹ OOPS!!! This task already been marked as completed.");
            } else if (cmd.getCommand().equals("unmark") && !t.isDone()) {
                throw new IllegalActionException("☹ OOPS!!! This task already been marked as incomplete.");
            } else {
                t.updateStatus();
                if (t.isDone()) {
                    result = "Nice! I've marked this task as done:\n\t   " + t.getDetails();
                } else {
                    result = "OK, I've marked this task as not done yet:\n\t   " + t.getDetails();
                }
                onTaskListChanged();
            }
        }
        
        return result;
    }

    public String getAddTaskResult(Command cmd) throws IllegalContentException {
        
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
            task = new Deadline(cmd.getDescription(), Parser.parseStringToDateTime(cmd.getDatetime()));
            break;
        case EVENT:
            task = new Event(cmd.getCommand(), Parser.parseStringToDateTime(cmd.getDatetime()));
            break;
        }
        
        if (task != null) {
            taskList.add(task);
    
            result = "Got it. I've added this task: \n\t   ";
            result += task.getDetails();
            result += "\n\t Now you have " + taskList.size() + " tasks in the list.";
            
//            PrintUtil.printAddedMessage(task, taskList.size());
            onTaskListChanged();
        }
        
        return result;
    }
    
    public String getDeleteTaskResult(Command cmd) throws IllegalContentException {
        
        String result = "";
        Task task;
        
        if (cmd.getIndex() == -1) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + cmd.getCommand() + " must be a number.");
        } else if (cmd.getIndex() > taskList.size()) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + cmd.getCommand() + " must be a number that is not larger than the list size.");
        } else {
            int index = cmd.getIndex();
            task = taskList.get(index);
            taskList.remove(index);
            
            result = "Noted. I've removed this task: \n\t   ";
            result += task.getDetails();
            result += "\n\t Now you have " + taskList.size() + " tasks in the list.";
            onTaskListChanged();
        }
        return result;
    }
    
    
    private void onTaskListChanged() {
        try {
            fileManager.writeToFile(getTasksFormattedString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    private String getTasksFormattedString() {
        StringBuilder result = new StringBuilder();
        
        for (Task task : taskList) {
            result.append(task.getSavingFormatDetails());
        }
        
        return result.toString();
    }
    
    private void convertDetailsToTaskList(ArrayList<String> taskDetails) throws IllegalContentException {
        for (String detail : taskDetails) {
            String[] parts = detail.split(" \\| ");
            switch (parts[0]) {
            case "T":
                taskList.add(new ToDo(parts[2], parts[1].equals("1")));
                break;
            case "E":
                taskList.add(new Event(parts[2], Parser.parseStringToDateTime(parts[3]), parts[1].equals("1")));
                break;
            case "D":
                taskList.add(new Deadline(parts[2], Parser.parseStringToDateTime(parts[3]), parts[1].equals("1")));
                break;
            default:
                taskList.add(new Task(parts[2], parts[1].equals("1")));
                break;
            }
        }
    }
    
    public String getFindTaskResult(Command cmd) {
        ArrayList<Task> resultArr = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDetails().contains(cmd.getDescription())) {
                resultArr.add(task);
            }
        }
        
        StringBuilder resultStr = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < resultArr.size(); i++) {
            Task t = resultArr.get(i);
            resultStr.append(String.format("\t %d.%s\n", i + 1, t.getDetails()));
        }
        return resultStr.toString();
    }
}
