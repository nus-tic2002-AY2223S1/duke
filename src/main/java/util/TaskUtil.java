package util;

import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;
import exception.EmptyContentException;
import exception.IllegalActionException;
import util.PrintUtil;

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

    public static void addTask(ArrayList<Task> taskList, String[] inputArr) throws EmptyContentException {

        if (inputArr.length <= 1) {
            throw new EmptyContentException("☹ OOPS!!! The description of a " + inputArr[0] + " cannot be empty.");
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
                throw new EmptyContentException("☹ OOPS!!! The due date/time of a deadline cannot be empty.");
            }
            task = new Deadline(words[0], words[1]);
        }


        if (inputArr[0].equals("event")) {
            String[] words = inputArr[1].split("/");
            if (words.length < 2) {
                throw new EmptyContentException("☹ OOPS!!! The date/time of a event cannot be empty.");
            }
            task = new Event(words[0], words[1]);
        }
        if (task != null) {

            taskList.add(task);
            PrintUtil.printAddedMessage(task, taskList.size());
        }
    }

}
