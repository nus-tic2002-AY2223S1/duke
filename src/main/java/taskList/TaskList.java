package taskList;


import parser.Parser;
import ui.UI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import static ui.ErrorMessages.*;
import static ui.TaskMessages.*;
import static ui.UI.*;

public class TaskList {
    //contains the task list e.g., it has operations to add/delete tasks in the list
    public static List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
        printMessage(MESSAGE_TASK_ADDED);
        System.out.println(task);
        System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public void deleteTask(String[] inputSplit) {
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit)) {
             try{
                int deleteIndex = Integer.parseInt(inputSplit[1]);
                Task task = taskList.get(deleteIndex - 1);
                taskList.remove(task);
                printMessage(MESSAGE_TASK_REMOVED);
                printLine();
                System.out.println(task);
                System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
                printLine();
             } catch (IndexOutOfBoundsException e) {
                 printError(TASK_NUMBER_OOB);
                 listTask(inputSplit);
             }
        }
        else printError(INVALID_DELETE_INPUT);
    }

    public void listTask(String[] inputSplit) {
        int taskCount = 0;
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit)) {
            printLine();
            System.out.println("Here are the tasks in your list:");
            for (Task task : taskList) {
                System.out.println(taskCount + 1 + ". " + task);
                taskCount++;
            }
            printLine();
        }
    }
    public void markTask(String[] inputSplit) {
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit)) {
            try {
                int markedIndex = Integer.parseInt(inputSplit[1]);
                Task markedTask = taskList.get(markedIndex - 1);
                markedTask.markAsUndone();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                printError(TASK_NUMBER_OOB);
                listTask(inputSplit);
            }
        }
    }

    public void unmarkTask(String[] inputSplit) {
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit)) {
            try {
                int unmarkedIndex = Integer.parseInt(inputSplit[1]);
                Task unmarkedTask = taskList.get(unmarkedIndex - 1);
                unmarkedTask.markAsUndone();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                printError(TASK_NUMBER_OOB);
                listTask(inputSplit);
            }
        }
    }


    public void todoTask(String input, String[] inputSplit) {
        try {
            if (checkValidCommand(inputSplit)) {
                printLine();
                addTask(Parser.parseTodoInput(input));
                printLine();
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            printLine();
            printError(INVALID_TODO_INPUT);
            printLine();
        }
    }

    public void deadlineTask(String input, String[] inputSplit) {
        try {
            if (checkValidCommand(inputSplit)) {
                printLine();
                addTask(Parser.parseDeadlineInput(input));
                printLine();
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            printLine();
            printError(INVALID_DEADLINE_INPUT);
            printLine();
        }
    }

    public void eventTask(String input, String[] inputSplit) {
        try {
            if (checkValidCommand(inputSplit)) {
                printLine();
                addTask(Parser.parseEventInput(input));
                printLine();
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            printLine();
            printError(INVALID_EVENT_INPUT);
            printLine();
        }
    }

    public boolean checkEmptyTaskList() {
        if (taskList.isEmpty()) {
            printLine();
            printError(NO_TASK_FOUND);
            printLine();
            return true;
        }
        return false;
    }

    public boolean checkValidCommand(String[] inputSplit) {
        if (inputSplit[0].equals("delete") && checkInteger(inputSplit[1])
        && inputSplit.length == 2 )
            return true;

        if (inputSplit[0].equals("todo") || inputSplit[0].equals("event") || inputSplit[0].equals("deadline")){
            if (inputSplit.length>1)
                return true;
        }

        if (inputSplit[0].equals("mark") && checkInteger(inputSplit[1])
                && inputSplit.length == 2 )
            return true;

        if (inputSplit[0].equals("unmark") && checkInteger(inputSplit[1])
                && inputSplit.length == 2 )
            return true;

        if (inputSplit[0].equals("list") && inputSplit.length == 1 )
            return true;

        if (inputSplit[0].equals("bye") && inputSplit.length == 1 )
            return true;

        else {
            printLine();
            printError(INVALID_INPUT);
            printLine();
            return false;}
    }

    public boolean checkInteger(String s){
        try{
            Integer.parseInt(s);
        }catch(Exception e ){
            return false;
        }
        return true;
    }


    public int size() {
        int count = taskList.size();
        return count;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}

