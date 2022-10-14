package manager;

import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;
import exception.IllegalContentException;
import exception.IllegalActionException;
import parser.Parser;
import util.PrintUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    private static TaskManager instance;
    private static ArrayList<Task> taskList;
    private FileManager fileManager;

    public TaskManager() {
        taskList = new ArrayList<>();
        fileManager = FileManager.getInstance();
        try {
            convertDetailsToTaskList(fileManager.readFromFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }  catch (IllegalContentException e) {
            PrintUtil.printErrorMessage(e);
        }
    }

    public static void newInstance() {
        instance = new TaskManager();
    }

    /**
     * @return
     */
    public static TaskManager getInstance() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }

    public void printList() {
        StringBuilder printStr = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            printStr.append(String.format("\t %d.%s\n", i + 1, t.getDetails()));
        }

        PrintUtil.printWithIndentation(printStr.toString());
    }

    public void updateTaskStatus(String[] inputArr) throws IllegalActionException {

        if (inputArr.length > 1) {
            int index = Integer.parseInt(inputArr[1]) - 1;
            Task t = taskList.get(index);
            if (inputArr[0].equals("mark") && t.isDone()) {
                throw new IllegalActionException("☹ OOPS!!! This task already been marked as completed.");
            } else if (inputArr[0].equals("unmark") && !t.isDone()) {
                throw new IllegalActionException("☹ OOPS!!! This task already been marked as incomplete.");
            } else {
                t.updateStatus();
                if (t.isDone()) {
                    PrintUtil.printWithIndentation("Nice! I've marked this task as done:\n\t   " + t.getDetails());
                } else {
                    PrintUtil.printWithIndentation("OK, I've marked this task as not done yet:\n\t   " + t.getDetails());
                }
                onTaskListChanged();
            }
        }
    }

    public void addTask(String[] inputArr) throws IllegalContentException {

        if (inputArr.length <= 1) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + inputArr[0] + " cannot be empty.");
        }

        Task task = null;

        if (inputArr[0].equals("add")) {
            task = new Task(inputArr[1]);
        }

        if (inputArr[0].equals("todo")) {
            task = new ToDo(inputArr[1]);
        }

        if (inputArr[0].equals("deadline")) {
            String[] words = inputArr[1].split("/by");
            if (words.length < 2) {
                throw new IllegalContentException("☹ OOPS!!! The due date/time of a deadline cannot be empty.");
            }
            task = new Deadline(words[0], Parser.parseStringToDateTime(words[1]));
        }


        if (inputArr[0].equals("event")) {
            String[] words = inputArr[1].split("/at");
            if (words.length < 2) {
                throw new IllegalContentException("☹ OOPS!!! The date/time of a event cannot be empty.");
            }
            task = new Event(words[0], Parser.parseStringToDateTime(words[1]));
        }
        if (task != null) {

            taskList.add(task);
            PrintUtil.printAddedMessage(task, taskList.size());
            onTaskListChanged();
        }
    }

    public void deleteTask(String[] inputArr) throws IllegalContentException {

        if (inputArr.length <= 1) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + inputArr[0] + " cannot be empty.");
        }

        Task task = null;
        String intExpr = "/\\d+";

        if (inputArr[1].matches(intExpr)) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + inputArr[0] + " must be a number.");
        } else if (Integer.parseInt(inputArr[1]) > taskList.size()) {
            throw new IllegalContentException("☹ OOPS!!! The description of a " + inputArr[0] + " must be a number that is not larger than the list size.");
        } else {
            int index = Integer.parseInt(inputArr[1]) - 1;
            task = taskList.get(index);
            taskList.remove(index);
            PrintUtil.printDeletedMessage(task, taskList.size());
            onTaskListChanged();
        }
    }

    private void onTaskListChanged() {
        try {
            fileManager.writeToFile(getTasksFormattedString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private String getTasksFormattedString() {
        StringBuilder result = new StringBuilder();

        for (Task task : taskList) {
            result.append(task.getSavingFormatDetails());
        }

        return result.toString();
    }

    private void convertDetailsToTaskList(ArrayList<String> taskDetails) throws IllegalContentException {
        for (String detail : taskDetails) {
            String[] parts = detail.split(" \\| ");
            switch (parts[0]) {
                case "T":
                    taskList.add(new ToDo(parts[2], parts[1].equals("1")));
                    break;
                case "E":
                    taskList.add(new Event(parts[2], Parser.parseStringToDateTime(parts[3]), parts[1].equals("1")));
                    break;
                case "D":
                    taskList.add(new Deadline(parts[2], Parser.parseStringToDateTime(parts[3]), parts[1].equals("1")));
                    break;
                default:
                    taskList.add(new Task(parts[2], parts[1].equals("1")));
                    break;
            }
        }
    }
}
