import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
       
        Task[] list = new Task[100];
        int next = 0, taskNo = 0;
        String key, new_input, x, y;

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
                System.out.println("Here are the task in your list:\n");
                for (int i = 0; i < next; i++) {
                    System.out.println(i+1 + ". " + list[i].description.toString());
                }
            }
            else if (key.equalsIgnoreCase("mark")) {
                taskNo = Integer.parseInt(tokens[1]) - 1;
                System.out.println("Nice! I've marked this task as done:");
                list[taskNo].mark();
                list[taskNo].description.replace(4, 5, "X");;
                System.out.println(list[taskNo].description.toString());
            }
            else if (key.equalsIgnoreCase("unmark")) {
                taskNo = Integer.parseInt(tokens[1]) - 1;
                System.out.println("OK, I've marked this task as not done yet:");
                list[taskNo].unmark();
                list[taskNo].description.replace(4, 5, " ");
                System.out.println(list[taskNo].description.toString());
            }
            else if (key.equalsIgnoreCase("todo")) {
                new_input = input.replace(key, "[T][ ]");
                list[next] = new Todo(new_input);
                next++;
            }
            else if (key.equalsIgnoreCase("deadline")) {
                new_input = input.replace(key, "[D][ ]");
                list[next] = new Deadline(new_input);
                next++;
            }
            else if (key.equalsIgnoreCase("event")) {
                new_input = input.replace(key, "[E][ ]");
                list[next] = new Event(new_input);
                next++;
            }
            else {
                    list[next] = new Task("[ ][ ] " + input);
                    next++;
                    System.out.println("added: " + input);
            }                
        }  
    }
}
