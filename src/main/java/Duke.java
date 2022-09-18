import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from YH\n" + logo);
        System.out.println("    ---------------------------------------");
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");

        List<Task> tasks = new ArrayList<>();
        String action, rest;
        whileLoop: while (true){
            String text;
            Scanner s = new Scanner(System.in);
            text = s.nextLine();
            Task t;
            action = text.split(" ", 2)[0];
            switch(action){
                case "bye":
                    System.out.println("    ---------------------------------------");
                    System.out.println("    Bye. Hope to see you again soon!");
                    break whileLoop;

                case "list":
                    DukeUtils.printList(tasks);
                    break;

                case "mark":
                    rest = text.split(" ", 2)[1];
                    t = tasks.get(Integer.parseInt(rest)-1);
                    t.updateMark(true);
                    DukeUtils.printText("Nice! I've marked this task as done:\n " + "    " + t.getStatusDescription());
                    break;

                case "unmark":
                    rest = text.split(" ", 2)[1];
                    t = tasks.get(Integer.parseInt(rest)-1);
                    t.updateMark(false);
                    DukeUtils.printText("OK, I've marked this task as not done yet:\n " + "    " + t.getStatusDescription());
                    break;

                default:
                    tasks.add(new Task(text));
                    DukeUtils.printText("added: " + text);
            }
        }
    }
}
