package parser;

import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;
import exception.DukeException;

import java.util.ArrayList;

/**
 * @author Yao Liang
 * @created 07/11/2022 - 2:27 pm
 * @projct Duke
 */
public class TaskParser {
    
    /**
     * Converts task info strings to task objects
     * Adds all task objects to taskList
     *
     * @param taskDetails tasks information list
     */
    public static ArrayList<Task> parseStringsToTasks(ArrayList<String> taskDetails) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String detail : taskDetails) {
            String[] parts = detail.split(" \\| ");
            switch (parts[0]) {
            case "T":
                tasks.add(new ToDo(parts[2], parts[1].equals("1")));
                break;
            case "E":
                tasks.add(new Event(parts[2], DatetimeParser.parseStringToDateTime(parts[3]), parts[1].equals("1")));
                break;
            case "D":
                tasks.add(new Deadline(parts[2], DatetimeParser.parseStringToDateTime(parts[3]), parts[1].equals("1")));
                break;
            default:
                tasks.add(new Task(parts[2], parts[1].equals("1")));
                break;
            }
        }
        return tasks;
    }
    
    
    /**
     * Reformats task list to string with saving format
     *
     * @return task list information that can be used to save locally
     */
    public static String parseTasksToStrings(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        
        for (Task task : tasks) {
            result.append(task.getSavingFormatDetails());
        }
        
        return result.toString();
    }
}
