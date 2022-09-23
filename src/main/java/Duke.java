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
        String action, rest = " ";
        whileLoop: while (true){
            String text;
            Scanner s = new Scanner(System.in);
            text = s.nextLine();
            Task t;
            action = text.split(" ", 2)[0];
            if(text.matches(".*\\s.*")){
                rest = text.split(" ", 2)[1];
            }

            switch(action){
                case "bye":
                    System.out.println("    ---------------------------------------");
                    System.out.println("    Bye. Hope to see you again soon!");
                    break whileLoop;

                case "list":
                    DukeUtils.printList(tasks);
                    break;

                case "mark":
                    t = tasks.get(Integer.parseInt(rest)-1);
                    t.updateMark(true);
                    DukeUtils.printText("Nice! I've marked this task as done:\n " + "    " + t.getStatusDescription());
                    break;

                case "unmark":
                    t = tasks.get(Integer.parseInt(rest)-1);
                    t.updateMark(false);
                    DukeUtils.printText("OK, I've marked this task as not done yet:\n " + "    " + t.getStatusDescription());
                    break;

                case "todo":
                    t = new Task(rest);
                    t.setType('T');
                    tasks.add(t);
                    int countT = tasks.size();
                    DukeUtils.printAction(t, countT);
                    break;

                case "deadline":
                    t = new Task(rest);
                    t.setType('D');
                    tasks.add(t);
                    int countD = tasks.size();
                    DukeUtils.printAction(t ,countD);
                    break;

                case "event":
                    t = new Task(rest);
                    t.setType('E');
                    tasks.add(t);
                    int countE = tasks.size();
                    DukeUtils.printAction(t, countE);
                    break;

                default:
                    tasks.add(new Task(text));
                    DukeUtils.printText("added: " + text);
            }
        }
    }
}
