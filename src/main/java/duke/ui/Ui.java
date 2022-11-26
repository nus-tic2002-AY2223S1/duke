package duke.ui;

import java.util.List;

import duke.data.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {

        private static String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        
        public Ui () {              

        }
        
        public static void welcome() {
            showLine();
            System.out.println(logo);
            showLine();
            System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
            System.out.println("*To get started, please type in your commannd and press enter.");
            System.out.println("To check the list of available commands, type help and then press enter.*\n");
        }

        public static void bye() {
            System.out.println("Bye. Hope to see you again soon!");
        }

        public static void showLine() {
            System.out.println("***************************");
        }

        public static void unknown() {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        
        public static void noSelection() {
            System.out.println("Selected task does not exist!"
                               + " Consider checking tasks with the list command and try again.\n");
        }

        public static void emptyList() {
            System.out.println("List is empty!\n");
        }

        public static void showList(TaskList list) {
            assert list.getCount() != 0;
            System.out.println("Here are the task in your list:");
            for (int i = 0; i < list.getCount(); i++) {
                System.out.println(list.getList().get(i).getTaskNo() + ". " + list.getList().get(i).getDescription());
            }
            System.out.println();
        }

        /**
         * Search the current list on the user string input
         * specified afterthe keyword.
         * @param keyword The input string that is to be searched.  
         *                Can be a single word or a long string.
         * @param list    The current list.
         */
        public static void findList(String keyword, TaskList list) {
            assert list.getCount() != 0;
            boolean found = false;
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < list.getCount(); i++) {
                if (list.getList().get(i).getDescription().contains(keyword.toLowerCase())) {
                    System.out.println(list.getList().get(i).getTaskNo() + ". " 
                                       + list.getList().get(i).getDescription());
                    found = true;
                    }
            }
            if (!found) {
                System.out.println("None found! Try again with another keyword :(");
            }
        System.out.println();
        }

        public static void addTask(Task type, TaskList list) {
            System.out.println("Got it. I've added this task:");
            list.addT(type);
            System.out.println(type.getDescription());
            System.out.println("Now you have " + list.getCount() + " in the list.\n");
        }

        public static String emptyTask(String key) {
            return "OOPS!!! The description of a " + key.toLowerCase() + " cannot be empty.\n";
        }

        public static void markTask(int id, List<Task> tl) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tl.get(id).getDescription().toString() + "\n");
        }

        public static void unmarkTask(int id, List<Task> tl) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tl.get(id).getDescription().toString() + "\n"); 
        }

        public static String trueMark(String key) {
            return "Task is already " + key.toLowerCase() + "ed!\n";
        }

        public static String eventTimeError() {
            return"\n\n**Event time should have a start and end time e.g 2-4pm.\n"
                   + " Consider using deadline command if there is no end time.**\n";
        }

        public static String deadlineTimeError() {
            return"\n\n**Deadline time should only have a single time value e.g 6pm.\n"
                   + " Consider using event command if it is a range of time.**\n";
        }
        
        public static void printHelp() {
            Storage.help();
            System.out.println();
        }
}
