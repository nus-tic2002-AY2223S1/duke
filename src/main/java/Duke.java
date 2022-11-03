import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Task;
import entities.Deadline;
import entities.Event;
import entities.Todo;
import exception.DukeException;
import utils.DukeUtils;

import static command.command.*;

public class Duke {

    private static final Scanner s = new Scanner(System.in);

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

        whileLoop:
        while (true) {
            String text = s.nextLine();
            try {
                DukeUtils.validateInput(text);
            } catch (DukeException e) {
                DukeUtils.printText(e.getMessage());
                continue;
            }

            action = text.split(" ", 2)[0];

            switch (action) {
                case "bye":
                    System.out.println("    ---------------------------------------");
                    System.out.println("    Bye. Hope to see you again soon!");
                    break whileLoop;

                case "list":
                    DukeUtils.printList(tasks);
                    break;

                case "mark":
                    rest = text.split(" ", 2)[1];
                    Task t = tasks.get(Integer.parseInt(rest) - 1);
                    t.updateMark(true);
                    DukeUtils.printText("Nice! I've marked this task as done:\n " + "    " + t.toString());
                    break;

                case "unmark":
                    rest = text.split(" ", 2)[1];
                    Task unmarkt = tasks.get(Integer.parseInt(rest) - 1);
                    unmarkt.updateMark(false);
                    DukeUtils.printText("OK, I've marked this task as not done yet:\n " + "    " + unmarkt.toString());
                    break;

                case "todo":
                    createTodo(text, tasks);
                    break;

                case "deadline":
                    createDeadline(text, tasks);
                    break;

                case "event":
                    createEvent(text, tasks);
                    break;

                case "delete":
                    deleteTask(text, tasks);
                    break;

                default:
                    tasks.add(new Task(text));
                    DukeUtils.printText("added: " + text);
            }
        }
    }
}
