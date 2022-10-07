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
        String text, key, rest;

        whileLoop: while (true) {
            text = inputText();
            try {
                DukeUtils.validateInputText(text);
            } catch (DukeException e) {
                DukeUtils.echoText(e.getMessage());
                continue;
            }
            key = text.split(" ", 2)[0];
            Task task;
            switch (key) {
                case "bye":
                    DukeUtils.echoText("Bye. Hope to see you again soon!");
                    break whileLoop;
                case "list":
                    DukeUtils.printList(tasks);
                    break;
                case "mark":
                    rest = text.split(" ", 2)[1];
                    task = tasks.get(Integer.parseInt(rest)-1);
                    task.markTask();
                    DukeUtils.echoText("Nice! I've marked this task as done:\n      "
                            + task);
                    break;
                case "unmark":
                    rest = text.split(" ", 2)[1];
                    task = tasks.get(Integer.parseInt(rest)-1);
                    task.unmarkTask();
                    DukeUtils.echoText("OK, I've marked this task as not done yet:\n      "
                            + task);
                    break;
                case "deadline":
                    rest = text.split(" ", 2)[1];
                    String deadline_desc = rest.split("/by", 2)[0];
                    String deadline_by = rest.split("/by", 2)[1];
                    Deadline deadline = new Deadline(deadline_desc, deadline_by);
                    tasks.add(deadline);
                    DukeUtils.echoText("Got it. I've added this task: \n      " +
                            deadline + "\n     Now you have "+ tasks.size()+" tasks in the list.");
                    break;
                case "todo":
                    rest = text.split(" ", 2)[1];
                    Todo todo = new Todo(rest);
                    tasks.add(todo);
                    DukeUtils.echoText("Got it. I've added this task: \n      " +
                            todo + "\n     Now you have "+ tasks.size()+" tasks in the list.");
                    break;
                case "event":
                    rest = text.split(" ", 2)[1];
                    String event_desc = rest.split("/at", 2)[0];
                    String event_at = rest.split("/at", 2)[1];
                    Event event = new Event(event_desc, event_at);
                    tasks.add(event);
                    DukeUtils.echoText("Got it. I've added this task: \n      " +
                            event + "\n     Now you have "+ tasks.size()+" tasks in the list.");
                    break;
                default:
                    tasks.add(new Task(text));
                    DukeUtils.echoText("added: " + text);
            }
        }

    }
    public static String inputText() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
