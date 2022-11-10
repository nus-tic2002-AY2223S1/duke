package taskList;


import parser.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    // to add task into task list and print message
    public void addTask(Task task) {
        taskList.add(task);
        printMessage(MESSAGE_TASK_ADDED);
        System.out.println(task);
        System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
    }

    // to delete task into task list and print message
    public void deleteTask(String input, String[] inputSplit) {
        // to ensure that list is not empty and whether the input is valid
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

    // to print the list of task
    public void listTask(String[] inputSplit, String input) {
        int taskCount = 0;
        // to ensure that list is not empty and whether the input is valid
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

    // to mark into task list and print message
    public void markTask(String[] inputSplit, String input) {
        // to ensure that list is not empty and whether the input is valid
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

    // to unmark into task list and print message
    public void unmarkTask(String[] inputSplit, String input) {
        // to ensure that list is not empty and whether the input is valid
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

    // to add todo task into task list and print message
    public void todoTask(String input, String[] inputSplit) {
        try {
            // to check if the input is valid
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

    // to add deadline task into task list and print message
    public void deadlineTask(String input, String[] inputSplit) {
        try {
            // to check if input is valid
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

    // to add event task into task list and print message
    public void eventTask(String input, String[] inputSplit) {
        try {
            // to check if input is valid
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

    // view task schedule
    public void scheduleTask(String input, String[] inputSplit) {
            // to check if input is valid
        String scheduleDate = Parser.parseScheduleInput(input);
        Integer count = 0;
            if (checkValidCommand(inputSplit, input)) {
                printMessage("Tasks on: " + Parser.StringToDate(scheduleDate));
                for (Task task : taskList){
                    if (Parser.StringToDate(scheduleDate).isEqual(task.getDate())) {
                        printMessage(task.toString());
                        count += 1;
                    }
                }
                if (count == 0)
                    printMessage("No task is found on " + Parser.StringToDate(scheduleDate));
            }
            else{
                printError(INVALID_INPUT);
            }
    }

    // check for empty list, return true if list is empty
    public boolean checkEmptyTaskList() {
        if (taskList.isEmpty()) {
            printLine();
            printError(NO_TASK_FOUND);
            printLine();
            return true;
        }
        return false;
    }

    // check for input, return true if input is valid
    public boolean checkValidCommand(String[] inputSplit, String input) {
        try {
            // if task name is delete, checks format of input
            if (inputSplit[0].equals("delete") && checkInteger(inputSplit[1])
                    && inputSplit.length == 2)
                return true;

            // if task name is delete and format of input
            if (inputSplit[0].equals("todo") && inputSplit.length > 1)
                return true;

            // if task name is deadline
            if (inputSplit[0].equals("deadline")) {
                // check if input contains "by", input format and date format
                if (!checkDeadlineContainsBy(input) || inputSplit.length < 2 || !checkDateInput(inputSplit,input)) {
                    printLine();
                    printError(INVALID_DEADLINE_INPUT);
                    printLine();
                    return false;
                } else return true;
            }

            // if task name is event
            if (inputSplit[0].equals("event")) {
                // check if input contains "at", input format and date format
                if (!checkEventContainsAt(input) || inputSplit.length < 2 || !checkDateInput(inputSplit,input)) {
                    printLine();
                    printError(INVALID_EVENT_INPUT);
                    printLine();
                    return false;
                }
                else return true;
            }

            // if taskname is schedule
            if (inputSplit[0].equals("schedule")){
                // checks for date format
                if (!checkDateInput(inputSplit,input)){
                    printLine();
                    printError(INVALID_INPUT);
                    printLine();
                    return false;
                }
                else return true;
            }
            // if task name is mark
            if (inputSplit[0].equals("mark")) {
                // check for mark input format
                if (checkInteger(inputSplit[1]) && inputSplit.length == 2)
                    return true;
            }
            // if task name is unmark
            if (inputSplit[0].equals("unmark")) {
                // check for unmark input format
                if (checkInteger(inputSplit[1]) && inputSplit.length == 2)
                return true;
            }
            // check for list input format
            if (inputSplit[0].equals("list") && inputSplit.length == 1)
                return true;

            // check for bye input format
            if (inputSplit[0].equals("bye") && inputSplit.length == 1)
                return true;

            else {
                // print error if input is invalid and return false
                printLine();
                printError(INVALID_INPUT);
                printLine();
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // print error if exception and return false
            printLine();
            printError(INVALID_INPUT);
            printLine();
            return false;
        }
    }

    // check if Event input contains "at", if contains at, return true
    public boolean checkEventContainsAt(String input){
        if (input.contains(" /at "))
            return true;
        else return false;
    }

    // check if Deadline input contains "/by", if contains by, return true
    public boolean checkDeadlineContainsBy(String input){
        if (input.contains(" /by "))
            return true;
        else return false;
    }

    // check if string is an integer
    public boolean checkInteger(String s){
        try{
            Integer.parseInt(s);
        }catch(Exception e ){
            return false;
        }
        return true;
    }

    // check if date is in the correct pattern ("dd-MM-yyyy"). if pattern matches input, return true
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
            if (inputSplit[0].equals("schedule")) {
                String scheduleDate = inputSplit[2];
                if (validateDateFormat(scheduleDate))
                    return scheduleDate.matches(datePattern);
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

    // to validate that date exists
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
            System.out.println(input+" is an INVALID Date format");
            return false;
        }
        /* Return true if date format is valid */
        return true;

    }


    // get task list size
    public int size() {
        return taskList.size();
    }

    // get task list
    public List<Task> getTaskList() {
        return this.taskList;
    }
}

