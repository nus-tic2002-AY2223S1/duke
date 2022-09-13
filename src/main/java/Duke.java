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
        String s, y;

        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            String[] tokens = input.split(" ");
            s = tokens[0];
            if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    in.close();
                    return;
            }
            else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the task in your list:\n");
                for (int i = 0; i < next; i++) {
                    System.out.println(i+1 + ". " + list[i].description);
                }
            }
            else if (s.equalsIgnoreCase("mark")) {
                taskNo = Integer.parseInt(tokens[1]);
                System.out.println("Nice! I've marked this task as done:\n");
                list[taskNo].mark();
                y = list[taskNo].description.substring(3);
                list[taskNo].description = "[" + list[taskNo].getStatusIcon() + "]" + y;
            }
            else if (s.equalsIgnoreCase("unmark")) {
                taskNo = Integer.parseInt(tokens[1]);
                System.out.println("OK, I've marked this task as not done yet:\n");
                list[taskNo].unmark();
                y = list[taskNo].description.substring(3);;
                list[taskNo].description = "[" + list[taskNo].getStatusIcon() + "]" + y;
            }
            else {
                    list[next] = new Task("[ ] " + input);
                    next++;
                    System.out.println("added: " + input);
            }                
        }  
    }
}
