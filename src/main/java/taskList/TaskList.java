package tasklist;


import parser.Parser;
import storage.Storage;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static ui.ErrorMessages.*;
import static ui.TaskMessages.*;
import static ui.UI.*;

public class TaskList {
    //contains the task list e.g., it has operations to add/delete tasks in the list
    public ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    // read task from text file and load tasks into task list
    public TaskList(File file) {
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                loadTask(line);
            }
            printLine();
            printMessage("All tasks have been loaded from " + Storage.path);
            printLine();
        } catch (Exception e) {
            System.out.println("Problem to open " + Storage.path + "\n" + e.getMessage());

        }
    }

    // retrieve task from text file, get task type to convert to load tasks into tasklist
    public void loadTask(String line) {
        try {
            // text file is split with ; delimeter
            String[] lineSplit = line.split(";");
            String taskName = lineSplit[0];
            // T for Todo Tasks
            if (taskName.equals("T"))
                loadTodoData(lineSplit[1], lineSplit[2]);

            // D for Deadline Tasks
            if (taskName.equals("D"))
                loadDeadlineData(lineSplit[1], lineSplit[2], lineSplit[3]);

            // E for Event Tasks
            if (taskName.equals("E"))
                loadEventData(lineSplit[1], lineSplit[2], lineSplit[3]);

            // F for Fixed Duration Tasks
            if (taskName.equals("F"))
                loadFixedData(lineSplit[1], lineSplit[2], lineSplit[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            printError(LOADING_ERROR + "ERROR ERROR");
        }

    }

    // convert Todo Task from String to Task
    public void loadTodoData(String mark, String input) {
        Todo loadTodo = new Todo(input);
        loadMark(mark, loadTodo);
        taskList.add(loadTodo);
        printMessage(loadTodo + " has been added.");
    }

    // convert Deadline Task from String to Task
    public void loadDeadlineData(String mark, String input, String date) {
        date = Parser.loadDateToString(date);
        Deadline loadDeadline = new Deadline(input, date);
        loadMark(mark, loadDeadline);
        taskList.add(loadDeadline);
        printMessage(loadDeadline + " has been added.");
    }

    // convert Event Task from String to Task
    public void loadEventData(String mark, String input, String date) {
        date = Parser.loadDateToString(date);
        Event loadEvent = new Event(input, date);
        loadMark(mark, loadEvent);
        taskList.add(loadEvent);
        printMessage(loadEvent + " has been added.");
    }

    // convert Fixed Duration Task from String to Task
    public void loadFixedData(String mark, String input, String time) {
        Fixed loadFixed = new Fixed(input, time);
        loadMark(mark, loadFixed);
        taskList.add(loadFixed);
        printMessage(loadFixed + " has been added.");
    }

    // check whether task in text file loaded is marked or unmarked
    public void loadMark(String mark, Task task) {
        if (mark.equals("1"))
            task.markAsDone();
        if (mark.equals("0"))
            task.markAsUndone();
    }

    // adds task into task list and print message
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
        }
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
                // retrieve index to be marked as done
                int markedIndex = Integer.parseInt(inputSplit[1]);

                // retrieve task to be marked
                Task markedTask = taskList.get(markedIndex - 1);

                // mark task as done
                markedTask.markAsDone();

                // output to indicate task is marked as done
                ui.UI.printLine();
                ui.UI.printMessage(MESSAGE_MARKED_TASK + markedTask.getDescription());
                ui.UI.printLine();
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
                // retrieve index to be unmarked
                int unmarkedIndex = Integer.parseInt(inputSplit[1]);

                // unmark task as done
                Task unmarkedTask = taskList.get(unmarkedIndex - 1);

                // unmark task
                unmarkedTask.markAsUndone();

                // output to indicate task is unmarked
                ui.UI.printLine();
                ui.UI.printMessage(MESSAGE_UNMARKED_TASK + unmarkedTask.getDescription());
                ui.UI.printLine();
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
            // to check if input is valid to add task
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
            // to check if input is valid to add task
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
            // to check if input is valid to add task
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

    // view task schedule on a certain date
    public void scheduleTask(String input, String[] inputSplit) {
        // to check if input is valid to add task
        boolean taskFound = false;
        try {
            if (checkValidCommand(inputSplit, input)) {
                String scheduleDate = Parser.parseScheduleInput(input);
                printMessage("Tasks on: " + Parser.stringToDate(scheduleDate));
                for (Task task : taskList) {
                    if (Parser.stringToDate(scheduleDate).equals(task.getDate())) {
                        printTask(String.valueOf(task));
                        taskFound = true;
                    }
                }
            }
        } catch (Exception e) {
            printLine();
            printError(INVALID_SCHEDULE_INPUT);
            printLine();
        }
        if (taskList.size() == 0 || !taskFound)
            printMessage("No task is found on " + Parser.stringToDate(Parser.parseScheduleInput(input)));
        printLine();
    }

    // find tasks according to user input
    public void findTask(String input, String[] inputSplit) {
        // to check if input is valid to add task
        Integer count = 0;
        if (!checkEmptyTaskList()) {
            if (checkValidCommand(inputSplit, input)) {
                printLine();
                String findInput = Parser.parseFindInput(input);
                printMessage(MESSAGE_FIND_TASK);
                for (Task task : taskList) {
                    if (task.getDescription().contains(findInput)) {
                        printTask(String.valueOf(task));
                        count += 1;
                    }
                }
                if (count == 0)
                    printError("There are no matching tasks.");
                printLine();
            }
        }
    }

    // add Fixed Duration Tasks
    public void fixedDurationTasks(String input, String[] inputSplit) {
        // to check if input is valid to add task
        try {
            if (checkValidCommand(inputSplit, input)) {
                printLine();
                addTask(Parser.parseFixedDurationInput(input)); // TO CHANGE!!!!!!
                printLine();
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | DateTimeParseException e) {
            printLine();
            printError(INVALID_FIXED_DURATION_INPUT);
            printLine();
        }
    }

    // check for empty list, return true if list is empty
    public boolean checkEmptyTaskList() {
        if (taskList.isEmpty()) {
            // print message and return true if task list is empty
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
            if (inputSplit[0].equals("delete")) {
                if (checkInteger(inputSplit[1]) && inputSplit.length == 2)
                    return true;
                else {
                    printLine();
                    printError(INVALID_DELETE_INPUT);
                    printLine();
                    return false;
                }
            }

            // if task name is todo and format of input
            if (inputSplit[0].equals("todo") && inputSplit.length > 1)
                return true;

            // if task name is deadline
            if (inputSplit[0].equals("deadline")) {
                // check if input contains "by", input format and date format
                if (!checkDeadlineContainsBy(input) || inputSplit.length < 2 || !checkDateInput(inputSplit, input)) {
                    printLine();
                    printError(INVALID_DEADLINE_INPUT);
                    printLine();
                    return false;
                } else return true;
            }

            // if task name is event
            if (inputSplit[0].equals("event")) {
                // check if input contains "at", input format and date format
                if (!checkEventContainsAt(input) || inputSplit.length < 2 || !checkDateInput(inputSplit, input)) {
                    printLine();
                    printError(INVALID_EVENT_INPUT);
                    printLine();
                    return false;
                } else return true;
            }

            if (inputSplit[0].equals("fixed")) {
                // check if input contains "at", input format and date format
                if (!checkFixedContainsAt(input) || inputSplit.length < 2) {
                    printLine();
                    printError(INVALID_FIXED_DURATION_INPUT);
                    printLine();
                    return false;
                } else return true;
            }

            // if task name is schedule
            if (inputSplit[0].equals("schedule")) {
                // checks for date format
                if (!checkDateInput(inputSplit, input) || inputSplit.length != 2) {
                    printLine();
                    printError(INVALID_SCHEDULE_INPUT);
                    printLine();
                    return false;
                } else
                    return true;
            }

            // if task name is mark
            if (inputSplit[0].equals("done")) {
                // check for mark input format
                if (checkInteger(inputSplit[1]) && inputSplit.length == 2)
                    return true;
            }

            // if task name is unmark
            if (inputSplit[0].equals("undone")) {
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

            // check for find input format
            if (inputSplit[0].equals("find")) {
                if (inputSplit.length > 1)
                    return true;
                if (inputSplit[1] == null)
                    return false;
            } else {
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
        return false;
    }

    // check if Event input contains "at", if contains at, return true
    public boolean checkEventContainsAt(String input) {
        return input.contains(" /at ");
    }

    // check if Deadline input contains "/by", if contains by, return true
    public boolean checkDeadlineContainsBy(String input) {
        return input.contains(" /by ");
    }

    public boolean checkFixedContainsAt(String input) {
        return input.contains(" /needs ");
    }

    // check if string is an integer
    public boolean checkInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // check if date is in the correct pattern ("dd-MM-yyyy"). if pattern matches input, return true
    public boolean checkDateInput(String[] inputSplit, String input) {
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
                String scheduleDate = input.substring(9);
                if (validateDateFormat(scheduleDate))
                    return scheduleDate.matches(datePattern);
            }
            return false;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    // to validate that date exists
    public boolean validateDateFormat(String input) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
        sdfrmt.setLenient(false);
        try {
            Date javaDate = sdfrmt.parse(input);
        }
        /* Date format is invalid */ catch (ParseException e) {
            System.out.println(input + " is an INVALID Date. Please enter date in dd-MM-yyyy.");
            return false;
        }
        /* Return true if date format is valid */
        return true;

    }

    // get task list
    public List<Task> getTaskList() {
        return this.taskList;
    }

    // when user wishes to exit the program
    public void exit() {
        printBye();
        System.exit(0);
    }
}

