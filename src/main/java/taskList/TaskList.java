package taskList;


import parser.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
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

    public void deleteTask(String[] inputSplit, String input) {
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit, input)) {
            try {
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
                listTask(inputSplit, input);
            }
        } else printError(INVALID_DELETE_INPUT);
    }

    public void listTask(String[] inputSplit, String input) {
        int taskCount = 0;
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit, input)) {
            printLine();
            System.out.println("Here are the tasks in your list:");
            for (Task task : taskList) {
                System.out.println(taskCount + 1 + ". " + task);
                taskCount++;
            }
            printLine();
        }
    }

    public void markTask(String[] inputSplit, String input) {
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit, input)) {
            try {
                int markedIndex = Integer.parseInt(inputSplit[1]);
                Task markedTask = taskList.get(markedIndex - 1);
                markedTask.markAsDone();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                printError(TASK_NUMBER_OOB);
                listTask(inputSplit, input);
            }
        }
    }

    public void unmarkTask(String[] inputSplit, String input) {
        if (!checkEmptyTaskList() && checkValidCommand(inputSplit, input)) {
            try {
                int unmarkedIndex = Integer.parseInt(inputSplit[1]);
                Task unmarkedTask = taskList.get(unmarkedIndex - 1);
                unmarkedTask.markAsUndone();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                printError(TASK_NUMBER_OOB);
                listTask(inputSplit, input);
            }
        }
    }

    public void todoTask(String input, String[] inputSplit) {
        try {
            if (checkValidCommand(inputSplit, input)) {
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
            if (checkValidCommand(inputSplit, input)) {
                printLine();
                addTask(Parser.parseDeadlineInput(input));
                printLine();
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | DateTimeParseException e) {
            printLine();
            printError(INVALID_DEADLINE_INPUT);
            printLine();
        }
    }

    public void eventTask(String input, String[] inputSplit) {
        try {
            if (checkValidCommand(inputSplit, input)) {
                printLine();
                addTask(Parser.parseEventInput(input));
                printLine();
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | DateTimeParseException e) {
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

    public boolean checkValidCommand(String[] inputSplit, String input) {
        try {
            if (inputSplit[0].equals("delete") && checkInteger(inputSplit[1])
                    && inputSplit.length == 2)
                return true;

            if (inputSplit[0].equals("todo") && inputSplit.length > 1)
                return true;

            if (inputSplit[0].equals("deadline")) {
                if (!checkDeadlineContainsBy(input) || inputSplit.length < 2 || !checkDateInput(inputSplit,input)) {
                    printLine();
                    printError(INVALID_DEADLINE_INPUT);
                    printLine();
                    return false;
                } else return true;
            }

            if (inputSplit[0].equals("event")) {
                if (!checkEventContainsAt(input) || inputSplit.length < 2 || !checkDateInput(inputSplit,input)) {
                    printLine();
                    printError(INVALID_EVENT_INPUT);
                    printLine();
                    return false;
                }
                else return true;
            }

            if (inputSplit[0].equals("mark")) {
                if (checkInteger(inputSplit[1]) && inputSplit.length == 2)
                    return true;
            }

            if (inputSplit[0].equals("unmark") && checkInteger(inputSplit[1]) && inputSplit.length == 2)
                return true;

            if (inputSplit[0].equals("list") && inputSplit.length == 1)
                return true;

            if (inputSplit[0].equals("bye") && inputSplit.length == 1)
                return true;

            else {
                printLine();
                printError(INVALID_INPUT);
                printLine();
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printLine();
            printError(INVALID_INPUT);
            printLine();
            return false;
        }
    }

    public boolean checkEventContainsAt(String input){
        if (input.contains(" /at "))
            return true;
        else return false;
    }
    public boolean checkDeadlineContainsBy(String input){
        if (input.contains(" /by "))
            return true;
        else return false;
    }

    public boolean checkInteger(String s){
        try{
            Integer.parseInt(s);
        }catch(Exception e ){
            return false;
        }
        return true;
    }

    public boolean checkDateInput(String[] inputSplit, String input){
        String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
        try {
            if (inputSplit[0].equals("deadline")) {
                String by = (input.substring(9)).split(" /by ")[1];
                if (validateDateFormat(by))
                    return by.matches(datePattern);
            }
            if (inputSplit[0].equals("event")) {
                String at = (input.substring(6)).split(" /at ")[1];
                if (validateDateFormat(at))
                    return at.matches(datePattern);
            }
            return false;
        }

        catch (IllegalArgumentException e){
            printLine();
            printError(INVALID_INPUT);
            printLine();
            return false;
        }
    }
    public boolean validateDateFormat(String input){
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
        sdfrmt.setLenient(false);
        try
        {
            Date javaDate = sdfrmt.parse(input);
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
            System.out.println(input+" is Invalid Date format");
            return false;
        }
        /* Return true if date format is valid */
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

