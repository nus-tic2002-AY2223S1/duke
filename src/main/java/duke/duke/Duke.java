package duke.duke;

import java.io.IOException;
import java.util.Scanner;

import duke.data.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.ui.Ui;
import duke.exceptions.DukeException;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
       
        TaskList list = new TaskList();
        String key, newInput;
        Scanner in = new Scanner(System.in);

        Ui.welcome();

        while(true) {
            String input = in.nextLine();
            String[] tokens = input.split(" ");
            key = tokens[0];

            if (key.equalsIgnoreCase("bye")) {
                Ui.bye();
                in.close();
                return;
            } else if (key.equalsIgnoreCase("help")) {
                Ui.printHelp();
            } else if (key.equalsIgnoreCase("list")) {
                if (list.getCount() == 0) {
                    Ui.emptyList();
                    continue;
                }   else {
                        Ui.showList(list);
                }
            } else if (key.equalsIgnoreCase("find")) {
                String keyString = input.substring(input.indexOf(" ") + 1);
                if (list.getCount() == 0) {
                    Ui.emptyList();
                    continue;
                }   else {
                        Ui.findList(keyString, list);
                }
            } else if (key.equalsIgnoreCase("delete")) {
                if (list.getCount() == 0) {
                    Ui.emptyList();
                    continue;
                }
                //check if selected task number is valid
                if (list.checkListNo(tokens[1])) {
                    Ui.noSelection();
                    continue;
                } else {
                    list.deleteTask(Integer.valueOf(tokens[1]));
                }
            } else if (key.equalsIgnoreCase("mark")) {
                if (list.getCount() == 0) {
                    Ui.emptyList();
                    continue;
                } else if (list.checkListNo(tokens[1])) {
                    Ui.noSelection();;
                    continue;
                }
                list.markT(Integer.valueOf(tokens[1]) - 1, key);
            } else if (key.equalsIgnoreCase("unmark")) {
                if (list.getCount() == 0) {
                    Ui.emptyList();
                    continue;
                } else if (list.checkListNo(tokens[1])) {
                    Ui.noSelection();
                    continue;
                }
                list.unmarkT(Integer.valueOf(tokens[1]) - 1, key);
            } else if (key.equalsIgnoreCase("todo")) {
                if (tokens[1].isEmpty()) {
                    System.out.println(Ui.emptyTask(key));
                    continue;
                }
                newInput = input.replace(key, "[T][ ]");
                Task temp = new Todo(newInput);
                Ui.addTask(temp, list);
            } else if (key.equalsIgnoreCase("deadline")) {
                if (tokens[1].isEmpty()) {
                    System.out.println(Ui.emptyTask(key));
                    continue;
                }
                newInput = input.replace(key, "[D][ ]");
                Task temp = new Deadline(newInput);
                Ui.addTask(temp, list);
            } else if (key.equalsIgnoreCase("event")) {
                if (tokens[1].isEmpty()) {
                    System.out.println(Ui.emptyTask(key));
                    continue;
                }
                newInput = input.replace(key, "[E][ ]");
                Task temp = new Event(newInput);
                Ui.addTask(temp, list);
            } else {
                Ui.unknown();
            }
            Storage.save("resources/mytasks.txt", list.getList()); 
        }  
    }
}
