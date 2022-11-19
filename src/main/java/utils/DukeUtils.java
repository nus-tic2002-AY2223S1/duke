package utils;

import java.util.List;
import entities.Task;
import entities.Todo;
import entities.Deadline;
import entities.Event;
import exception.DukeException;

public class DukeUtils {
    public static void printText(String text){
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + text);
        System.out.println(space + line);
    }
    public static void printAction(Task t, int count){
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + "Got it. I've added this task:" );
        System.out.println(space + t.toString());
        System.out.println(space + "Now you have" + count + "tasks in the list." );
        System.out.println(space + line);
    }
    public static void printDelete(Task t, int count){
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + "Noted. I've removed this task:" );
        System.out.println(space + t.toString());
        System.out.println(space + "Now you have" + count + "tasks in the list." );
        System.out.println(space + line);
    }
    public static String getAction(String description){
        String action = description.split(" ", 2)[0].trim();
        return action;
    }
    public static String getBody(String description){
        String rest = description.split(" ", 2)[1].trim();
        String body = rest.split("/", 2)[0].trim();
        return body;
    }
    public static String getDate(String description) {
        String date = description.split("/", 2)[1].trim();
        return date;
    }
    public static void printList(List<Task> tasks) {
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + "Here are the tasks in your list:");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(space + index + ". " + task.toString());
        }
        System.out.println(space + line);
    }
    public static void validateInput(String text) throws DukeException {
        String action;
        action = text.split(" ", 2)[0];
        switch(action) {
            case "bye":
            case "list":
                return;
            case "deadline":
                if (text.split(" ", 2).length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if (!text.contains("/")) {
                    throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                }
                break;
            case "todo":
                if (text.split(" ", 2).length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "event":
                if (text.split(" ", 2).length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                if (!text.contains("/")) {
                    throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
                }
                break;
            case "delete":
                if (text.split(" ", 2).length < 2) {
                    throw new DukeException("☹ OOPS!!! The delete task number cannot be empty.");
                }
                break;
            case "mark":
                if (text.split(" ", 2).length < 2) {
                    throw new DukeException("☹ OOPS!!! The mark task number cannot be empty.");
                }
                break;
            case "unmark":
                if (text.split(" ", 2).length < 2) {
                    throw new DukeException("☹ OOPS!!! The unmark task number cannot be empty.");
                }
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
