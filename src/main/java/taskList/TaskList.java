package taskList;


import parser.Parser;
import ui.UI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static ui.ErrorMessages.*;
import static ui.TaskMessages.*;
import static ui.UI.*;

public class TaskList {
    //contains the task list e.g., it has operations to add/delete tasks in the list
    public static List<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(Task task) {
        taskList.add(task);
        printMessage(MESSAGE_TASK_ADDED);
        System.out.println(task);
        System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public void deleteTask(String[] lineSpaceSplit) {
        try {
            if (!checkEmptyTaskList() && (Integer.parseInt(String.valueOf(lineSpaceSplit[0].length()))) == 6) {
                int deleteIndex = Integer.parseInt(lineSpaceSplit[1]);
                Task task = taskList.get(deleteIndex - 1);
                taskList.remove(task);
                printMessage(MESSAGE_TASK_REMOVED);
                printLine();
                System.out.println(task);
                System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");

            }
        } catch (IndexOutOfBoundsException e) {
            printLine();
            printError(TASK_NUMBER_OOB);
            listTask();
            printLine();
        }
    }

    public void listTask() {
        int taskCount = 0;
        ui.UI.printLine();
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(taskCount + 1 + ". " + task);
            taskCount++;
        }
        printLine();
    }
    public void markTask(String line) {
        if (!checkEmptyTaskList()) {
            try {
                String[] lineSpaceSplit = line.split(" ");
                if (Integer.parseInt(String.valueOf(lineSpaceSplit[0].length())) == 4) {
                    int markedIndex = Integer.parseInt(lineSpaceSplit[1]);
                    try {
                        Task markedTask = taskList.get(markedIndex - 1);
                        markedTask.markAsDone();
                    } catch (IndexOutOfBoundsException e) {
                        printLine();
                        printError(TASK_NUMBER_OOB);
                        listTask();
                    }
                } else {
                    printLine();
                    printError(INVALID_MARK_INPUT);
                    printLine();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.printArrayOOB();
            }
        }
    }

    public void unmarkTask(String[] inputSplit) {
        if (!checkEmptyTaskList()) {
            try {
                if (Integer.parseInt(String.valueOf(inputSplit[0].length())) == 6) {
                    int unmarkedIndex = Integer.parseInt(inputSplit[1]);
                    try {
                        Task unmarkedTask = taskList.get(unmarkedIndex - 1);
                        unmarkedTask.markAsUndone();
                    } catch (IndexOutOfBoundsException e) {
                        printLine();
                        printError(TASK_NUMBER_OOB);
                        listTask();
                    }
                } else {
                    printLine();
                    printError(INVALID_UNMARK_INPUT);
                    printLine();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.printArrayOOB();
            }
        }
    }

    public void todoTask(String input, String[] inputSplit) {
        ui.UI.printLine();
        if (inputSplit[0].length() == 4) {
            try {
//                String todoTask = input.substring(5);
//                Todo newTodoTask = new Todo(todoTask);
                addTask(Parser.parseTodoInput(input));
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                printError(INVALID_TODO_INPUT);
            }
        } else printError(INVALID_TODO_INPUT);
        printLine();
    }

    public void deadlineTask(String input, String[] inputSplit) {
        printLine();
        if (inputSplit[0].length() ==8) {
            try {
                String[] deadlineItemSplit = (input.substring(9)).split(" /by ");
                String deadlineTask = deadlineItemSplit[0];
                String deadlineBy = deadlineItemSplit[1];
//                LocalDateTime deadlineDate = LocalDateTime.parse(deadlineBy);
                Deadline task = new Deadline(deadlineTask, deadlineBy);
                addTask(task);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                printError(INVALID_DEADLINE_INPUT);
            }
        } else printError(INVALID_DEADLINE_INPUT);
        printLine();
    }

    public void eventTask(String input, String[] inputSplit) {
        printLine();
        if (inputSplit[0].length() == 5) {
            try {
                String[] eventItemSplit = (input.substring(6)).split(" /at ");
                String eventTask = eventItemSplit[0];
                String eventAt = eventItemSplit[1];
                Event task = new Event(eventTask, eventAt);
                addTask(task);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                printTask(INVALID_EVENT_INPUT);
            }
        } else printError(INVALID_EVENT_INPUT);
        printLine();
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

    public int size() {
        int count = taskList.size();
        return count;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}

