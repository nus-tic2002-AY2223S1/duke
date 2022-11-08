import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
       
        Collections list = new Collections();
        //Task[] list = new Task[100];
        int next = 0;
        String key, new_input;

        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            String[] tokens = input.split(" ");
            key = tokens[0];
            if (key.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    in.close();
                    return;
            }
            else if (key.equalsIgnoreCase("list")) {
                if (list.getCount() == 0) {
                    System.out.println("List is empty!");
                    continue;
                }   else {
                        System.out.println("Here are the task in your list:\n");
                    for (int i = 1; i <= list.getCount(); i++) {
                        System.out.println(i + ". " + list.getTasks().get(i-1).getDescription());
                    }
                }
            }
            else if (key.equalsIgnoreCase("delete")) {
                if (tokens[1].isEmpty() || Integer.parseInt(tokens[1]) > next) {
                    throw new ArrayIndexOutOfBoundsException("Selected task does not exist");
                } else {
                    list.deleteTask(Integer.valueOf(tokens[1]));
                }
                
            }
            else if (key.equalsIgnoreCase("mark")) {
                list.markTask(Integer.valueOf(tokens[1]) - 1);
            }
            else if (key.equalsIgnoreCase("unmark")) {
                    int j = Integer.valueOf(tokens[1]) - 1;
                    list.unmarkTask(Integer.valueOf(tokens[1]) - 1);
            }
            else if (key.equalsIgnoreCase("todo")) {
                if (tokens[1].isEmpty()) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                new_input = input.replace(key, "[T][ ]");
                Task temp = new Todo(new_input);
                list.addTask(temp);
                next++;
            }
            else if (key.equalsIgnoreCase("deadline")) {
                if (tokens[1].isEmpty()) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                new_input = input.replace(key, "[D][ ]");
                Task temp = new Deadline(new_input);
                list.addTask(temp);
                next++;
            }
            else if (key.equalsIgnoreCase("event")) {
                if (tokens[1].isEmpty()) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                new_input = input.replace(key, "[E][ ]");
                Task temp = new Event(new_input);
                list.addTask(temp);
                next++;
            }
            else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }                
        }  
    }
}
