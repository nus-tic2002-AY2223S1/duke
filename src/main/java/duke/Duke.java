package duke;

import java.util.Scanner;

import duke.tasks.*;
import duke.ui.Ui;
import duke.data.Storage;
import duke.exceptions.DukeException;

public class Duke {
    public static void main(String[] args) throws Exception {
       
        TaskList list = new TaskList();
        String key, new_input;
        Scanner in = new Scanner(System.in);

        Ui.showLine();
        Ui.welcome();
        Ui.showLine();


        while(true) {
            String input = in.nextLine();
            String[] tokens = input.split(" ");
            key = tokens[0];

            if (key.equalsIgnoreCase("bye")) {
                Ui.bye();
                in.close();
                return;
            }

            else if (key.equalsIgnoreCase("list")) {
                if (list.getCount() == 0) {
                    Ui.emptyList();
                    continue;
                }   else {
                        Ui.showList(list);
                }
            }

            else if (key.equalsIgnoreCase("delete")) {
                if (tokens[1].isEmpty() || Integer.parseInt(tokens[1]) > list.getCount()) {
                    Ui.noSelection();
                    continue;
                } else {
                    list.deleteTask(Integer.valueOf(tokens[1]));
                }
            }

            else if (key.equalsIgnoreCase("mark")) {
                list.markT(Integer.valueOf(tokens[1]) - 1, key);
            }

            else if (key.equalsIgnoreCase("unmark")) {
                    list.unmarkT(Integer.valueOf(tokens[1]) - 1, key);
            }

            else if (key.equalsIgnoreCase("todo")) {
                if (tokens[1].isEmpty()) {
                    Ui.emptyTask(key);
                    continue;
                }
                new_input = input.replace(key, "[T][ ]");
                Task temp = new Todo(new_input);
                Ui.addTask(temp, list);
            }

            else if (key.equalsIgnoreCase("deadline")) {
                if (tokens[1].isEmpty()) {
                    Ui.emptyTask(key);
                    continue;
                }
                new_input = input.replace(key, "[D][ ]");
                Task temp = new Deadline(new_input);
                Ui.addTask(temp, list);
            }

            else if (key.equalsIgnoreCase("event")) {
                if (tokens[1].isEmpty()) {
                    Ui.emptyTask(key);
                    continue;
                }
                new_input = input.replace(key, "[E][ ]");
                Task temp = new Event(new_input);
                Ui.addTask(temp, list);
            }

            else {
                    Ui.unknown();
            }

            Storage.save("duke/mytasks.txt", list.getList()); 
        }  
    }
}
