import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static String breakLine = "     -------------------------------------------------";
    public static void main(String[] args) {
        // DUKE greeting message
        DukeUtils.dukeInit();
        List<Task> tasks = new ArrayList<>();
        String key, text, rest;

        whileLoop: while (true) {
            text = inputText();
            key = text.split(" ", 2)[0];
            Task task;
            System.out.println(breakLine);
            switch (key) {
                case "bye":
                    echoText("Bye. Hope to see you again soon!");
                    break whileLoop;
                case "list":
                    printList(tasks);
                    break;
                case "mark":
                    rest = text.split(" ", 2)[1];
                    task = tasks.get(Integer.parseInt(rest)-1);
                    task.markTask();
                    echoText("Nice! I've marked this task as done:\n      "
                            + task);
                    break;
                case "unmark":
                    rest = text.split(" ", 2)[1];
                    task = tasks.get(Integer.parseInt(rest)-1);
                    task.unmarkTask();
                    echoText("OK, I've marked this task as not done yet:\n      "
                            + task);
                    break;
                default:
                    tasks.add(new Task(text));
                    echoText("added: " + text);
            }
            System.out.println(breakLine);
        }

    }
    public static String inputText() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static void echoText(String text){
        String tab = "     ";
        System.out.println(tab + text);
    }

    public static void printList(List<Task> tasks) {
        String tab = "     ";
        int counter = 0;
        for (Task task : tasks)
            System.out.println(tab + ++counter + ". " + task);
    }

}
