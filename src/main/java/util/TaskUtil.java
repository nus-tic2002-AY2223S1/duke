package util;

import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;
import exception.IllegalContentException;
import exception.IllegalActionException;

import java.util.ArrayList;

public class TaskUtil {

    public static void printList(ArrayList<Task> taskList) {
        StringBuilder printStr = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            printStr.append(String.format("\t %d.%s\n", i + 1, t.getDetails()));
        }

        PrintUtil.printWithIndentation(printStr.toString());
    }

    public static void updateTaskStatus(ArrayList<Task> taskList, String[] inputArr) throws IllegalActionException {

        if (inputArr.length > 1) {
            int index = Integer.parseInt(inputArr[1]) - 1;
            Task t = taskList.get(index);
            if (inputArr[0].equals("mark") && t.isDone()) {
                throw new IllegalActionException("☹ OOPS!!! This task already been marked as completed.");
            } else if (inputArr[0].equals("unmark") && !t.isDone()) {
                throw new IllegalActionException("☹ OOPS!!! This task already been marked as incomplete.");
            } else {
                taskList.get(index).updateStatus();
            }
        }
    }

    public static void addTask(ArrayList<Task> taskList, String[] inputArr) throws IllegalContentException {

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
            String[] words = inputArr[1].split("/");
            if (words.length < 2) {
                throw new IllegalContentException("☹ OOPS!!! The due date/time of a deadline cannot be empty.");
            }
            task = new Deadline(words[0], words[1]);
        }


        if (inputArr[0].equals("event")) {
            String[] words = inputArr[1].split("/");
            if (words.length < 2) {
                throw new IllegalContentException("☹ OOPS!!! The date/time of a event cannot be empty.");
            }
            task = new Event(words[0], words[1]);
        }
        if (task != null) {

            taskList.add(task);
            PrintUtil.printAddedMessage(task, taskList.size());
        }
    }

    public static void deleteTask(ArrayList<Task> taskList, String[] inputArr) throws IllegalContentException {

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
        }
    }
}
