package taskList;

import java.util.ArrayList;
import java.util.List;

import static ui.ErrorMessages.*;
import static ui.TaskMessages.*;
import static ui.UI.*;

public class TaskList {
    //contains the task list e.g., it has operations to add/delete tasks in the list
    public static List<Task> taskList = new ArrayList<>();

    public static void addTask(Task task){
        taskList.add(task);
        printMessage(MESSAGE_TASK_ADDED);
        System.out.println(task);
        System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void deleteTask(String[] lineSpaceSplit) {
        printLine();
        int deleteIndex = Integer.parseInt(lineSpaceSplit[1]);
        try {
            Task task = taskList.get(deleteIndex - 1);
            taskList.remove(task);
            printMessage(MESSAGE_TASK_REMOVED);
            System.out.println(task);
            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            printLine();
            printError(TASK_NUMBER_OOB);
            listTask();
            printLine();
        }
    }
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
            printError(TASK_NUMBER_OOB);
            listTask();
            printLine();
        }
    }

    public static void unmarkTask(String[] lineSpaceSplit) {
        int unmarkedIndex = Integer.parseInt(lineSpaceSplit[1]);
        try {
            Task unmarkedTask = taskList.get(unmarkedIndex - 1);
            unmarkedTask.markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            printError(TASK_NUMBER_OOB);
            listTask();
            printLine();
        }
    }

    public static void todoTask(String line) {
        ui.UI.printLine();
        try {
            String todoTask = line.substring(5);
            Todo newTodoTask = new Todo(todoTask);
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
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            printError(INVALID_DEADLINE_INPUT);
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
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            printTask(INVALID_EVENT_INPUT);
        }
        printLine();
    }
}

