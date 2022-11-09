package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

public class Ui {

        private static String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        
        public Ui () {              

        }

        public static void welcome() {
                System.out.println(logo);
                System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
        }

        public static void bye() {
                System.out.println("Bye. Hope to see you again soon!");
        }

        public static void showLine() {
                System.out.println("***************************");
        }

        public static void unknown() {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        
        public static void noSelection() {
                System.out.println("Selected task does not exist!");
        }

        public static void emptyList() {
                System.out.println("List is empty!");
        }

        public static void showList(TaskList list) {
                System.out.println("Here are the task in your list:\n");
                for (int i = 1; i <= list.getCount(); i++) {
                        System.out.println(i + ". " + list.getList().get(i-1).getDescription());
                }
        }

        public static void addTask(Task type, TaskList list) {
                System.out.println("Got it. I've added this task:");
                list.addT(type);
                System.out.println(type.getDescription());
                System.out.println("Now you have " + list.getCount() + " in the list.");
        }

        public static void emptyTask(String key) {
                System.out.println ("OOPS!!! The description of a " + key + " cannot be empty.");
        }

        public static void markTask(int id, List<Task> tl) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tl.get(id).getDescription().toString()); 
        }

        public static void unmarkTask(int id, List<Task> tl) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tl.get(id).getDescription().toString()); 
        }

        public static void trueMark(String key) {
                System.out.println("Task is already " + key.toLowerCase() + "ed!");
        }
}
