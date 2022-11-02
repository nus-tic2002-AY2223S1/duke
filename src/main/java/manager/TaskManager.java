package manager;

import entity.*;
import exception.IllegalContentException;
import exception.IllegalActionException;
import parser.Parser;
import util.CommandType;
import util.PrintUtil;

import java.io.File;
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
    
    public void printList() {
        StringBuilder printStr = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            printStr.append(String.format("\t %d.%s\n", i + 1, t.getDetails()));
        }
        
        PrintUtil.printWithIndentation(printStr.toString());
    }
    
    public void printFindResult(ArrayList<Task> result) {
        StringBuilder printStr = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < result.size(); i++) {
            Task t = result.get(i);
            printStr.append(String.format("\t %d.%s\n", i + 1, t.getDetails()));
        }
        
        PrintUtil.printWithIndentation(printStr.toString());
    }
    
    public void updateTaskStatus(Command cmd) throws IllegalActionException {
        
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
                    PrintUtil.printWithIndentation("Nice! I've marked this task as done:\n\t   " + t.getDetails());
                } else {
                    PrintUtil.printWithIndentation("OK, I've marked this task as not done yet:\n\t   " + t.getDetails());
                }
                onTaskListChanged();
            }
        }
    }
    
    public void addTask(Command cmd) throws IllegalContentException {
        
        Task task = null;
        
        switch (CommandType.valueOf(cmd.getCommand())) {
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
            PrintUtil.printAddedMessage(task, taskList.size());
            onTaskListChanged();
        }
    }
    
    public void deleteTask(Command cmd) throws IllegalContentException {
        
        Task task = null;
        String intExpr = "/\\d+";
        
        if (cmd.getIndex() == -1) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + cmd.getCommand() + " must be a number.");
        } else if (cmd.getIndex() > taskList.size()) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + cmd.getCommand() + " must be a number that is not larger than the list size.");
        } else {
            int index = cmd.getIndex();
            task = taskList.get(index);
            taskList.remove(index);
            PrintUtil.printDeletedMessage(task, taskList.size());
            onTaskListChanged();
        }
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
    
    public void findTask(Command cmd) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDetails().contains(cmd.getDescription())) {
                result.add(task);
            }
        }
        printFindResult(result);
    }
}
