package nus.duke.tasklist;

import nus.duke.parser.Parser;
import nus.duke.ui.Messages;
import nus.duke.ui.Ui;
import nus.duke.data.DukeException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;


/**
 * Represents a TaskList.
 */
public class TaskList {
    /** Container for tasks.  */
    static private ArrayList<Task> list;
    /** Keep track of number of tasks in the list.  */
    static private int listCount;
    static public final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    static public final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    /**
     * Constructor for TaskList.
     */
    public TaskList(){
        list = new ArrayList<Task>();
        listCount = 0;
    }
    /**
     * Constructor for TaskList while loading from .txt file.
     */
    public TaskList(TaskList load) {
        this.list = load.list;
        this.listCount = load.listCount;
    }
    /**
     * Getter for the listCount of the TaskList.
     */
    public static int getListCount(){
        return listCount;
    }
    /**
     * Getter for the Task container.
     */
    public ArrayList<Task> getList(){
        return list;
    }
    /**
     * Load tasks from the .txt file and add it to the TaskList.
     *
     * @throws DukeException forward the error message from the methods used.
     */
    static public void loadTasksFromFiles(String[] task) throws DukeException{
        ArrayList<String> descriptionTime;
        switch (task[0]){
            case "T":
                list.add(new Todo(task[2]));
                listCount ++;
                break;
            case"D":
                descriptionTime = splitDescriptionTimeForDeadline(task[2]);
                list.add(new Deadline(descriptionTime.get(0), descriptionTime.get(1),
                        LocalDateTime.parse(descriptionTime.get(2) + " " + descriptionTime.get(3), STORAGE_FORMATTER)));
                listCount ++;
                break;
            case "E":
                descriptionTime = splitDescriptionTimeForEvent(task[2]);
                list.add(new Event(descriptionTime.get(0), descriptionTime.get(1),
                        LocalDateTime.parse(descriptionTime.get(2) + " " + descriptionTime.get(3), STORAGE_FORMATTER),
                        LocalDateTime.parse(descriptionTime.get(4) + " " + descriptionTime.get(5), STORAGE_FORMATTER)));
                listCount ++;
                break;
            case"A":
                String taskBefore = null;
                descriptionTime = splitDescriptionTimeForDoAfter(task[2]);
                if (descriptionTime.size() == 4 && Parser.isDate(descriptionTime.get(2).replace("-","/"))){
                    list.add(new DoAfter(descriptionTime.get(0),
                            LocalDateTime.parse(descriptionTime.get(2) + " " + descriptionTime.get(3), STORAGE_FORMATTER)));
                } else {
                    for (int i = 2; i < descriptionTime.size(); i++){
                         taskBefore += descriptionTime.get(i);
                    }
                    list.add(new DoAfter(descriptionTime.get(0), taskBefore));
                }
                listCount ++;
                break;
            default:
                //Only tasks with case "T" ,"D", "E" and "A" should be loaded from a .txt file.
                assert false : task[0];
                return;
        }
        if (task[1].equals("X")){
            list.get(listCount - 1).markAsDone(1);
        }
        Ui.echoTasksLoadingFromFile(list.get(listCount - 1));
    }
    /**
     * Separate the description and date/time for deadline task type loaded from .txt file.
     *
     * @param description that contains the task description of a deadline type loaded from the .txt file.
     * @return an array of string contains the split descriptions.
     */
    public static ArrayList<String> splitDescriptionTimeForDeadline (String description) {
        String[] split = description.split(" \\(", 2);
        split[1] = split[1].substring(0, split[1].length() - 1);
        split[1] = split[1].replaceFirst(":", "");
        ArrayList<String> splitFurther = new ArrayList<String> (Arrays.asList(split[1].split(" ")));
        splitFurther.add(0 , split[0].trim());
        return splitFurther;
    }
    /**
     * Separate the description and date/time for event task type loaded from .txt file.
     *
     * @param description that contains the task description of a event type loaded from the .txt file.
     * @return an array of string contains the split descriptions.
     */
    public static ArrayList<String> splitDescriptionTimeForEvent(String description) {
        String[] split = description.split(" \\(", 2);
        split[1] = split[1].substring(0, split[1].length() - 1);
        split[1] = split[1].replaceFirst(":", "");
        ArrayList<String> splitFurther = new ArrayList<String> (Arrays.asList(split[1].split(" ")));
        String[] split2 = splitFurther.get(2).split("-", 2);
        splitFurther.set(2 , split2[0]);
        splitFurther.add(3 , split2[1]);
        splitFurther.add(0 , split[0].trim());
        return splitFurther;
    }
    /**
     * Separate the description and date/time or task before for DoAfter task type loaded from .txt file.
     *
     * @param description that contains the task description of a DoAfter type loaded from the .txt file.
     * @return an array of string contains the split descriptions.
     */
    public static ArrayList<String> splitDescriptionTimeForDoAfter (String description) {
        String[] split = description.split(" \\(", 2);
        split[1] = split[1].substring(0, split[1].length() - 1);
        split[1] = split[1].replaceFirst(":", "");
        ArrayList<String> splitFurther = new ArrayList<String> (Arrays.asList(split[1].split(" ")));
        splitFurther.add(0 , split[0].trim());
        return splitFurther;
    }
    /**
     * Add or deduct listCount while a change of TaskList occurred.
     */
    public void changeListCount (String sign){
        switch (sign){
            case "+":
                this.listCount ++;
                break;
            case "-":
                this.listCount --;
                break;
            default:
                break;
        }
    }
    /**
     * Print all tasks from the TaskList.
     */
    public void printList(){
        if (listCount == 0) {
            Ui.print(Messages.MESSAGE_NO_TASK_IN_LIST);
            return;
        }
        Ui.print("Here are the tasks in your list:\n");
        for(int i = 0; i < listCount; i++) {
            String count = (i + 1) + ". ";
            Ui.print(count);
            list.get(i).printTask();
        }
    }

}