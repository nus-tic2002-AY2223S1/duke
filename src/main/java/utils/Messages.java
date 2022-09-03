package utils;

import domain.task.Task;

public class Messages {
    public static String alreadyDoneMsg(Task task) {
        return "??? Hello??\n" + task + "\nAlready marked as done la... zzz...";
    }

    public static String markDoneMsg(Task task) {
        return "OK, I've marked this task as done:\n" + task;
    }

    public static String alreadyNotDoneMsg(Task task) {
        return "??? Hello??\n" + task + "\nAlready marked as not done la... zzz...";
    }

    public static String markUndoneMsg(Task task) {
        return "Nice! I've marked this task as not done yet:\n" + task;
    }

    public static String addedTaskMsg(Task task) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + (Task.getCount()) + " tasks in the list.";
    }

}
