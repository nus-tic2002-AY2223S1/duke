package util;

import entity.Task;

public class PrintUtil {
    public static String breaker = "\t____________________________________________________________\n";

    public static void printWithIndentation(String content) {
        System.out.printf("%s\t %s\n%s", breaker, content, breaker);

    }

    public static void printAddedMessage(Task task, int total) {
        String content = "Got it. I've added this task: \n\t   ";
        content += task.getDetails();
        content += "\n\t Now you have " + total + " tasks in the list.";
        printWithIndentation(content);
    }

    public static void printDeletedMessage(Task task, int total) {
        String content = "Noted. I've removed this task: \n\t   ";
        content += task.getDetails();
        content += "\n\t Now you have " + total + " tasks in the list.";
        printWithIndentation(content);
    }

    public static void printByeMessage() {
        printWithIndentation("Bye. Hope to see you again soon!");
    }

    public static void printIllegalActionMessage() {
        printWithIndentation("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printErrorMessage(Throwable e) {
        printWithIndentation(e.getMessage());
    }

    public static void printEmptyActionMessage() {
        printWithIndentation("☹ OOPS!!! The action cannot be empty.");
    }
}


