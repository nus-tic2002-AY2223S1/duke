package duke.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates an ArrayList of duke.task.Task objects to store the List of duke.task.Task.
 * Encapsulates the methods such as List, Add, Delete, Mark and Un-mark to manipulate the ArrayList.
 * Saves the ArrayList to a local directory and overwrites the existing file.
 */
public class TaskList {
    List<Task> tasks = new ArrayList<>();

    /**
     * Prints out the contents of the ArrayList of duke.task.Task with ordered numbering.
     * Prints out and error message "The list is empty" if it is an empty list.
     */
    public void listTask() {
        System.out.println("\t-----------------------------------------------------------------");
        // if list is empty, prints empty list message
        if (tasks.size() == 0) {
            System.out.println("\t The list is empty");
        } else {
            // prints out the entire list
            for (int i = 0; i < tasks.size(); i++) {
                int count = i + 1;
                System.out.println("\t " + count + ". " + tasks.get(i));
            }
        }
        System.out.println("\t-----------------------------------------------------------------");
    }

    /**
     * Adds a To-do duke.task.Task to the ArrayList.
     * @param lineArray an Array to store the result of a string split.
     */
    public void addToDo(String[] lineArray) {
        // piece the task description back together
        String descriptionLine = "";
        for (int i = 1; i < lineArray.length; i++) {
            descriptionLine += " " + lineArray[i];
        }
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t " + "Got it. I've added this task: ");
        tasks.add(new Todo(descriptionLine));
        System.out.println("\t\t " + tasks.get(tasks.size()-1));
        System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    /**
     * Adds a duke.task.Deadline duke.task.Task to the ArrayList.
     * @param lineArray an Array to store the result of a string split.
     */
    public void addDeadLine(String[] lineArray) {
        // piece the task description back together
        String descriptionLine = "";
        int indexOfDelimiter = 0;
        for (int i = 1; i < lineArray.length; i++) {
            if (lineArray[i].equalsIgnoreCase("/by")) {
                indexOfDelimiter = i;
                break;
            }
            descriptionLine += " " + lineArray[i];
        }
        String date = lineArray[indexOfDelimiter+1];
        String time = lineArray[indexOfDelimiter+2];
        String dateTime = date + "T" + time;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t " + "Got it. I've added this task: ");
        tasks.add(new Deadline(descriptionLine, localDateTime));
        System.out.println("\t\t " + tasks.get(tasks.size()-1));
        System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    /**
     * Adds an duke.task.Event duke.task.Task to the ArrayList.
     * @param lineArray an Array to store the result of a String split
     */
    public void addEvent(String[] lineArray) {
        String descriptionLine = "";
        int indexOfDelimiter = 0;
        for (int i = 1; i < lineArray.length; i++) {
            if (lineArray[i].equalsIgnoreCase("/at")) {
                indexOfDelimiter = i;
                break;
            }
            descriptionLine += " " + lineArray[i];
        }
        String date = lineArray[indexOfDelimiter+1];
        String time = lineArray[indexOfDelimiter+2];
        String dateTime = date + "T" + time;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t " + "Got it. I've added this task: ");
        tasks.add(new Event(descriptionLine, localDateTime));
        System.out.println("\t\t " + tasks.get(tasks.size()-1));
        System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    /**
     * Marks a duke.task.Task as completed in the ArrayList.
     * @param lineArray a String array to store the result of a String split
     */
    public void markTask(String[] lineArray) {
        // get the index of the to do list to mark
        int inputIndex = Integer.parseInt(lineArray[1]);
        int arrayIndex = inputIndex - 1;
        tasks.get(arrayIndex).setDone(true);
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + tasks.get(arrayIndex));
        System.out.println("\t-----------------------------------------------------------------");
    }

    /**
     * Marks a duke.task.Task as uncomplete in the ArrayList.
     * @param lineArray a String array to store the result of a String split
     */
    public void unmarkTask(String[] lineArray) {
        // get the index of the to do list to unmark
        int inputIndex = Integer.parseInt(lineArray[1]);
        int arrayIndex = inputIndex - 1;
        tasks.get(arrayIndex).setDone(false);
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t\t " + tasks.get(arrayIndex));
        System.out.println("\t-----------------------------------------------------------------");
    }

    /**
     * Deletes a duke.task.Task in the ArrayList.
     * @param lineArray a String array to store the result of a String split
     */
    public void deleteTask(String[] lineArray) {
        int inputIndex = Integer.parseInt(lineArray[1]);
        int arrayIndex = inputIndex - 1;
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t\t " + tasks.get(arrayIndex));
        tasks.remove(arrayIndex);
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    /**
     * Saves the ArrayList to a local directory and overwrites existing file.
     * @param fileDirectory file path of the local directory to where the file is store.
     */
    public void saveTaskList(String fileDirectory) throws IOException {
        FileWriter w = new FileWriter(fileDirectory);
        BufferedWriter bw = new BufferedWriter(w);
        for (int i = 0; i < tasks.size(); i++ ) {
            String line = String.valueOf(i+1) + "." + " " + tasks.get(i);
            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    /**
     * Checks if there are existing content in the file.
     * Adds existing content in the file to the ArrayList.
     * @param existingContent ArrayList of duke.task.Task object.
     */
    public void existingTaskList(List<Task> existingContent) {
        if (!existingContent.equals(null)) {
            for (int i = 0; i < existingContent.size(); i++) {
                tasks.add(existingContent.get(i));
            }
        }
    }

}
