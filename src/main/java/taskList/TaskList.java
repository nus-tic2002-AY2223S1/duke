package taskList;

import java.util.ArrayList;
import java.util.List;

import static ui.ErrorMessages.*;
import static ui.UI.*;

public class TaskList {
    //contains the task list e.g., it has operations to add/delete tasks in the list
    public static List<Task> taskList = new ArrayList<>();

    public static void addTask(Task task){
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
    }

//    public void deleteTask(int deleteIndex){
//            Task task = taskList.get(deleteIndex - 1);
//            taskList.remove(task);
//    }

    public static void listTask() {
        int taskCount = 0;
        ui.UI.printLine();
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(taskCount + 1 + ". " + task);
            taskCount++;
        }
        printLine();
    }

    public static void markTask(String[] lineSpaceSplit) {
        int markedIndex = Integer.parseInt(lineSpaceSplit[1]);
        try {
            Task markedTask = taskList.get(markedIndex - 1);
            markedTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task Number out of range. Please enter a valid input (Task Number: 1 " + "-" + taskList.size() + ")");
            ui.UI.printLine();
        }
    }

    public static void unmarkTask(String[] lineSpaceSplit) {
        int unmarkedIndex = Integer.parseInt(lineSpaceSplit[1]);
        try {
            Task unmarkedTask = taskList.get(unmarkedIndex - 1);
            unmarkedTask.markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task Number out of range. Please enter a valid input (Current Range: 1 " + "to " + taskList.size() + ")");
        }
    }

    public static void todoTask(String line) {
        ui.UI.printLine();
        try {
            String todoTask = line.substring(5);
            Todo newTodoTask = new Todo(todoTask);
//            taskList.add(newTodoTask);
//            System.out.println("Got it. I've added this task:");
//            System.out.println(newTodoTask);
//            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
            addTask(newTodoTask);
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            printError(INVALID_TODO_INPUT);
        }
        printLine();
    }

    public static void deadlineTask(String line) {

        printLine();
        try {
            String[] deadlineItemSplit = (line.substring(9)).split(" /by ");
            String deadlineTask = deadlineItemSplit[0];
            String deadlineBy = deadlineItemSplit[1];
            Deadline task = new Deadline(deadlineTask, deadlineBy);
            addTask(task);
//            taskList.add(task);
//            System.out.println("Got it. I've added this task:");
//            System.out.println(task);
//            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            String m = "Invalid Deadline input.\nPlease enter a valid input (E.g. deadline return book /by Sunday).";
            printError(m);
        }
        printLine();
    }

    public static void eventTask(String line) {
        printLine();
        try {
            String[] eventItemSplit = (line.substring(6)).split(" /at ");
            String eventTask = eventItemSplit[0];
            String eventAt = eventItemSplit[1];
            Event task = new Event(eventTask, eventAt);
            addTask(task);
//            taskList.add(task);
//            System.out.println("Got it. I've added this task:");
//            System.out.println(task);
//            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            printTask(INVALID_EVENT_INPUT);
        }
        printLine();
    }

    public static void deleteTask(String[] lineSpaceSplit) {
        printLine();
        int deleteIndex = Integer.parseInt(lineSpaceSplit[1]);
        try {
            Task task = taskList.get(deleteIndex - 1);
            taskList.remove(task);
            System.out.println("Noted. I've removed this task:\n");
            System.out.println(task);
            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            printLine();
            printError(TASK_NUMBER_OOB);
            printLine();
        }
    }

}

