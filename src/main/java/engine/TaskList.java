package engine;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static formatting.Helper.*;
/**
 * The TaskList class contains an ArrayList of Tasks, <code>TaskList</code>.
 * The <code>TaskList</code> is private and hence cannot be modified unless accessed by a method such as addNewTask() and deleteTask().
 * The class also has methods for displaying the task list, displaying a filtered task list, snoozing a task and marking a task as done or not done.
 */

public class TaskList {
    //Consists of operations involving the modification of the task list
    //stores the list of tasks
    //Performs add/remove task operations for all task types.
    //Shows to do list

    private static ArrayList<Task> TaskList;
    private static TaskList instance = null;

    //constructor
    private TaskList() {
        TaskList = new ArrayList<>();
    }


    public static TaskList getInstance(){
        if (instance == null){
            instance = new TaskList();
        }
        return instance;
    }

    public Object get(int taskIndex) {
        return TaskList.get(taskIndex);
    }

    public int getTLSize(){
        return TaskList.size();
    }

    public String getTodoListRow(int index){
        return index + 1 +"."+TaskList.get(index).toString();
    }


    public void addNewTask(String incomingTaskName, String incomingType, LocalDateTime fromDate, LocalDateTime toDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("d/MM/yyyy HHmm");
        if ("event".equals(incomingType)) {
            TaskList.add(new Event(incomingTaskName, fromDate, toDate));
        }

        separator();
        System.out.println("Added: " + TaskList.size() + "." + incomingTaskName +  " (at: " + fromDate.format(formatter) + " to " + toDate.format(formatter) +  ")" + "\nYou have " + TaskList.size()+ " tasks in the list!");
        separator();
    }

    public void addNewTask(String incomingTaskName, String incomingType, LocalDateTime incomingDate) {
        if ("deadline".equals(incomingType)) {
            TaskList.add(new Deadline(incomingTaskName, incomingDate));
        }

        separator();
        System.out.println("added: " + TaskList.size() + "." + incomingTaskName +  " (by: " + incomingDate.format(FORMATTER) + ")" + "\nYou have " + TaskList.size()+ " tasks in the list!");
        separator();
    }

    public void addNewTask(String incomingTaskName){

        TaskList.add(new Todo(incomingTaskName));

        separator();
        System.out.println("added: " + TaskList.size() + "." + incomingTaskName + "\nYou have " + TaskList.size()+ " tasks in the list!");
        separator();
    }

    public void deleteTask(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            separator();
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(. Task " + TaskIndex + " is NOT deleted.");
            showTodoList();
            separator();
        }
        else{
            String toBeDeleted = TaskList.get(i).toString();
            TaskList.remove(i);
            separator();
            System.out.println("Done! " +TaskIndex+". "+toBeDeleted+" has been deleted. The task list has been updated: ");
            showTodoList();
        }
    }

    public void showTodoList(){
        separator();
        for (int i = 0; i< TaskList.size(); i++){

            System.out.println(i+1+"."+TaskList.get(i).toString());

        }
        System.out.println("There are " + TaskList.size() + " tasks in the list!");
        separator();
    }

    public void showFilteredTodoList(String keyword){
        int size = 0;
        separator();
        for (int i = 0; i< TaskList.size(); i++){
            if (TaskList.get(i).getTaskName().contains(keyword)){
                size++;
                System.out.println(size+"."+TaskList.get(i).toString());
            }
        }
        if (size == 0) {
            System.out.println("No tasks found!");
        } else {
            System.out.println("There are " + size + " tasks in the list containing " + keyword + "!");
            separator();
        }
    }



    public void changeToMarkAsDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            separator();
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
            showTodoList();
            separator();
        }
        else{
            if (TaskList.get(i).isMarkAsDone().equals(" ")){
                TaskList.get(i).setMarkAsDone();
                separator();
                System.out.println("Nice! I've marked this task as done: ");
            }
            else {
                separator();
                System.out.println("This task has already been marked as done!");
            }
            System.out.println(TaskIndex+". "+TaskList.get(i).toString());
            separator();
        }

    }

    public void changeToMarkNotDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
            showTodoList();
        }
        else{
            if (TaskList.get(i).isMarkAsDone().equals("X")){
                TaskList.get(i).setMarkNotDone();
                separator();
                System.out.println("Meow, I've marked this task as not done yet: ");
            }
            else {
                separator();
                System.out.println("This task has already been marked as not done!");
            }
            System.out.println(TaskIndex+". "+TaskList.get(i).toString());
            separator();
        }

    }

    public void snoozeDeadline(int index, int duration, String unitTime){
        LocalDateTime updatedDeadline;
        LocalDateTime tempDeadline = (LocalDateTime) TaskList.get(index).getAt();

        if (unitTime.equals("weeks")){
            updatedDeadline = tempDeadline.plusWeeks(duration);
            TaskList.get(index).setAt(updatedDeadline);
        } else if (unitTime.equals("days")){
            updatedDeadline = tempDeadline.plusDays(duration);
            TaskList.get(index).setAt(updatedDeadline);
        } else if (unitTime.equals("hours")){
            updatedDeadline = tempDeadline.plusHours(duration);
            TaskList.get(index).setAt(updatedDeadline);
        } else if (unitTime.equals("minutes")){
            updatedDeadline = tempDeadline.plusMinutes(duration);
            TaskList.get(index).setAt(updatedDeadline);
        }
        System.out.println("Snoozed task " + (index+1) + " for " + duration + " " + unitTime);
        showTodoList();

    }


}
